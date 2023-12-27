package org.wceng.component;

import org.wceng.listener.LayerListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProcessLayer {

    ProcessExecutor processExecutor;

    ProcessCreator processCreator;

    LayerListener layerListener;

    DataManager dataManager;

    public ProcessLayer(ProcessCreator processCreator, LayerListener layerListener) {
        this.processCreator = processCreator;
        this.layerListener = layerListener;
        this.processExecutor = new ProcessExecutor();
        this.dataManager = new DataManager(processCreator.getProcessClass());
    }

    public void setUp(List<String> urls) throws Exception {
        for (String url : urls) {
            createSetupProcess(url);
        }
        processExecutor.shutdown();

        try {
            boolean tasksCompleted = processExecutor.awaitTermination(1, TimeUnit.HOURS); // 等待所有任务执行完成或超时
            if (tasksCompleted) {
                layerListener.onLayerExecuteFinished(this, dataManager);
            } else {
                System.out.println("Some tasks are still running or timed out.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createSetupProcess(String url) throws Exception {
        Process process = processCreator.create();
        process.setConnector(new Connector(url));
        process.setDataManager(dataManager);
        processExecutor.execute(process);
    }

}
