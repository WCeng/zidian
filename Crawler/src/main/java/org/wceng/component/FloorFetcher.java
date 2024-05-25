package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class FloorFetcher implements LayerFetcher {

    private final ProcessLayer processLayer;
    private final ProcessExecutor processExecutor;
    private final ProcessManager processManager;
    private final List<String> nextLayerUrls;
    private final ProcessCreator processCreator;
    private final LayerManager layerManager;


    public FloorFetcher(ProcessLayer processLayer, ProcessExecutor processExecutor) {
        this.processLayer = processLayer;
        this.processExecutor = processExecutor;
        this.processManager = new ProcessManager(processLayer);
        this.nextLayerUrls = new ArrayList<>();
        this.processCreator = processLayer.getProcessCreator();
        this.layerManager = processLayer.getChain().getLayerManager();
    }

    @Override
    public ProcessLayer getProcessLayer() {
        return processLayer;
    }

    @Override
    public void onListenProcessCompleted(Process process) {
        processManager.removeRunningProcess(process);

        Bundler bundler = process.getBundler();

        List<String> nextLayerUrls = bundler.getNextLayerUrls();
        List<String> loopLayerUrls = bundler.getLoopLayerUrl();

        if (!nextLayerUrls.isEmpty()) {
            this.nextLayerUrls.addAll(nextLayerUrls);
        }

        if (!loopLayerUrls.isEmpty()) {
            layerManager.launch(processLayer, loopLayerUrls);
        }


        if (!processManager.hasRunningProcess()) {
            layerManager.markCompleted(processLayer);
            if (layerManager.hasNextLayer(processLayer)) {
                layerManager.launchNext(processLayer, this.nextLayerUrls);
            } else {
                processExecutor.shutdown();
            }
        }
    }

    @Override
    public void fetch(List<String> urls) {
        try {
            List<Process> processes = processCreator.createProcesses(urls);
            processExecutor.executeProcesses(processes);
            processManager.addRunningProcesses(processes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
