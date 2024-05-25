package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class ProcessLayer implements ProcessExecutor.ProcessFinishedListener {

    private final ProcessCreator processCreator;

    private final Class<? extends Process> processClass;
    private final ProcessChain chain;
    private boolean isCompleted;
    private final BundlerBuffer bundlerBuffer;
    private final List<String> nextLayerUrls;
    private final boolean allowFetchLayered;
    private final LayerFetcher layerFetcher;

    public ProcessLayer(final Class<? extends Process> processClass, ProcessChain chain) {
        this.processClass = processClass;
        this.chain = chain;
        this.processCreator = new ProcessCreator(this);
//        this.processManager = new ProcessManager();
        this.bundlerBuffer = new BundlerBuffer(this);
        this.nextLayerUrls = new ArrayList<>();
        this.allowFetchLayered = chain.isAllowFetchLayered();
        this.isCompleted = false;

        if (allowFetchLayered) {
            this.layerFetcher = new FloorFetcher(this, chain.getProcessExecutor());
        } else {
            this.layerFetcher = new GradualFetcher(this, chain.getProcessExecutor());
        }
        this.chain.getProcessExecutor().addProcessFinishedListener(processClass, this);
    }

    public void launchWith(List<String> urls) throws Exception {
        layerFetcher.fetch(urls);
    }

    @Override
    public synchronized void onProcessFinished(Process process) {
        layerFetcher.onListenProcessCompleted(process);

        bundlerBuffer.cache(process.getBundler());

//        监听数据
        if (null != chain.getLayerListener()) {
            LayerListener.LayerChecker checker =
                    new LayerListener.LayerChecker(processClass, chain.getLayerManager().index(this));
            LayerInfo info = new LayerInfo(
                    processClass,
                    isCompleted,
                    bundlerBuffer.getTotalBundlerCachedCount(),
                    bundlerBuffer.getCurrentBundler(),
                    0,
                    process.getDocTitle());
            chain.getLayerListener().onLayerRunning(checker, info);

            if (isCompleted) {
                chain.getLayerListener().onLayerCompleted(checker, info);
            }

            if (process.getException() != null) {
                chain.getLayerListener().onLayerError(checker, process.getUrl(), process.getException());
            }
        }

    }

    public final static class LayerInfo {
        public final Class<? extends Process> processClass;
        public final boolean isCompleted;
        public final long cachedBundlerCount;
        public final Bundler currentBundler;
        public final long exceptionProcessCount;
        public final String currentProcessDocTitle;

        LayerInfo(Class<? extends Process> processClass,
                  boolean isCompleted,
                  long cachedBundlerCount,
                  Bundler currentBundler,
                  long exceptionProcessCount,
                  String currentProcessDocTitle) {
            this.processClass = processClass;
            this.isCompleted = isCompleted;
            this.cachedBundlerCount = cachedBundlerCount;
            this.currentBundler = currentBundler;
            this.exceptionProcessCount = exceptionProcessCount;
            this.currentProcessDocTitle = currentProcessDocTitle;
        }
    }

    public Class<? extends Process> getProcessClass() {
        return processClass;
    }

    void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public BundlerBuffer getBundlerBuffer() {
        return bundlerBuffer;
    }

    public ProcessChain getChain() {
        return chain;
    }

    public ProcessCreator getProcessCreator() {
        return processCreator;
    }
}
