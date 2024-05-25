package org.wceng.component;

import java.util.List;

public class GradualFetcher implements LayerFetcher {

    private final ProcessLayer processLayer;
    private final ProcessExecutor processExecutor;
    private final ProcessManager processManager;
    private final ProcessCreator processCreator;
    private final LayerManager layerManager;

    public GradualFetcher(ProcessLayer processLayer, ProcessExecutor processExecutor) {
        this.processLayer = processLayer;
        this.processExecutor = processExecutor;
        this.processManager = new ProcessManager(processLayer);
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
            if (layerManager.hasNextLayer(processLayer)) {
                layerManager.launchNext(processLayer, nextLayerUrls);
            }
        }

        if (!loopLayerUrls.isEmpty()) {
            layerManager.launch(processLayer, loopLayerUrls);
        }


        ProcessLayer preLayer = layerManager.preLayer(processLayer);
        ProcessLayer nextLayer = layerManager.nextLayer(processLayer);

        if (!processManager.hasRunningProcess()) {
            if (preLayer == null) {
                layerManager.markCompleted(processLayer);
                if (nextLayer == null) {
                    processExecutor.shutdown();
                }
            } else if (preLayer.isCompleted()) {
                layerManager.markCompleted(processLayer);
                if(nextLayer == null){
                    processExecutor.shutdown();
                }
            }
        }

//        if

//        if (layerManager.index(processLayer) == 0) {
//            if (!processManager.hasRunningProcess()) {
//                if (!layerManager.hasNextLayer(processLayer)) {
//                    layerManager.markCompleted(processLayer);
//                    processExecutor.shutdown();
//                } else {
//                    if (layerManager.nextLayer(processLayer).isCompleted()) {
//                        layerManager.markCompleted(processLayer);
//                        processExecutor.shutdown();
//                    }
//                }
//            }
//        } else if (layerManager.index(processLayer) < layerManager.count() - 1) {
//            if (!processManager.hasRunningProcess()) {
//                if (layerManager.preLayer(processLayer).isCompleted()
//                        && layerManager.nextLayer(processLayer).isCompleted()) {
//                    layerManager.markCompleted(processLayer);
//                    processExecutor.shutdown();
//                }
//            }
//        } else {
//            if (!processManager.hasRunningProcess()) {
//                if(layerManager.preLayer(processLayer).isCompleted()){
//                    layerManager.markCompleted(processLayer);
//                    processExecutor.shutdown();
//                }
//            }
//        }


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
