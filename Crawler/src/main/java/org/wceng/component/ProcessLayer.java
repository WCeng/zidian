package org.wceng.component;

import org.wceng.listener.LayerListener;

import java.util.List;

public class ProcessLayer implements ProcessExecutor.ProcessFinishedListener {

    private final ProcessCreator processCreator;
    private final ProcessManager processManager;

    final Class<? extends Process> processClass;
    final ProcessChain chain;
    boolean isCompleted;
    private final BundlerBuffer bundlerBuffer;

    public final static class LayerInfo {
        public final Class<? extends Process> processClass;
        public final boolean isCompleted;
        public final long cachedBundlerCount;
        public final Bundler currentBundler;
        public final long exceptionProcessCount;

        public LayerInfo(Class<? extends Process> processClass,
                         boolean isCompleted,
                         long cachedBundlerCount,
                         Bundler currentBundler,
                         long exceptionProcessCount) {
            this.processClass = processClass;
            this.isCompleted = isCompleted;
            this.cachedBundlerCount = cachedBundlerCount;
            this.currentBundler = currentBundler;
            this.exceptionProcessCount = exceptionProcessCount;
        }
    }

    public ProcessLayer(final Class<? extends Process> processClass, ProcessChain chain) {
        this.processClass = processClass;
        this.chain = chain;
        this.processCreator = new ProcessCreator(this);
        this.processManager = new ProcessManager();
        this.bundlerBuffer = new BundlerBuffer(this);

        chain.getProcessExecutor().addProcessFinishedListener(processClass, this);
    }

    public void fetch(List<String> urls) throws Exception {
        List<Process> processes = processCreator.createProcesses(urls);
        chain.getProcessExecutor().executeProcesses(processes);
        processManager.addProcesses(processes);
    }

    @Override
    public synchronized void onProcessFinished(Process process) {
        process.setCompleted(true);
        processManager.removeProcess(process);
        if (process.getException() != null) {
            processManager.addExceptProcess(process);
        }

        //检查当前layer是否需要循环或是否需要打开下层layer
        checkLayerLaunch(process.getBundler());

        //判断当前layer是否完成
        if (checkLayerComplete()) {
            isCompleted = true;
        }

        //缓存数据
        bundlerBuffer.cache(process.getBundler());

        //监听数据
        if (null != chain.getLayerListener()) {
            LayerListener.LayerChecker checker =
                    new LayerListener.LayerChecker(processClass, chain.getLayerManager().index(this));
            LayerInfo info = new LayerInfo(
                    processClass,
                    isCompleted,
                    bundlerBuffer.getTotalBundlerCachedCount(),
                    bundlerBuffer.getCurrentBundler(),
                    processManager.getExceptProcessCount());
            chain.getLayerListener().onLayerRunning(checker, info);

            if (isCompleted) {
                chain.getLayerListener().onLayerCompleted(checker, info);
            }

            if (process.getException() != null) {
                chain.getLayerListener().onLayerError(checker, process.getUrl(), process.getException());
            }
        }
    }

    private boolean checkLayerComplete() {
        LayerManager layerManager = chain.getLayerManager();
        int index = layerManager.index(this);
        if (index - 1 >= 0) {
            ProcessLayer preLayer = layerManager.get(index - 1);
            boolean completed = preLayer.isCompleted();
            boolean b = !processManager.hasRunningProcess();
            return completed && b;
        } else {
            return !processManager.hasRunningProcess();
        }
    }

    private void checkLayerLaunch(Bundler bundler) {
        LayerLauncher layerLauncher = chain.getLayerLauncher();
        LayerManager layerManager = chain.getLayerManager();

        int index = layerManager.index(this);

        List<String> nextLayerUrls = bundler.getNextLayerUrls();
        List<String> loopLayerUrls = bundler.getLoopLayerUrl();

        if (!nextLayerUrls.isEmpty()) {
            if (index < layerManager.count() - 1) {
                layerLauncher.launch(layerManager.get(index + 1), nextLayerUrls);
            }
        }

        if (!loopLayerUrls.isEmpty()) {
            layerLauncher.launch(layerManager.get(index), loopLayerUrls);
        }
    }

    public Class<? extends Process> getProcessClass() {
        return processClass;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ProcessCreator getProcessCreator() {
        return processCreator;
    }

    public BundlerBuffer getBundlerBuffer() {
        return bundlerBuffer;
    }
}
