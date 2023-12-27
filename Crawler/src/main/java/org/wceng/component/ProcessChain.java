package org.wceng.component;

import org.wceng.listener.DataListener;
import org.wceng.listener.LayerListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ProcessChain implements LayerListener {
    String baseUrl;
    int currentLevel;
    LinkedList<ProcessLayer> processLayerLinkedList;
    DataListener dataListener;

    public ProcessChain(String baseUrl) {
        this.baseUrl = baseUrl;
        this.currentLevel = -1;
        this.processLayerLinkedList = new LinkedList<>();
    }

    public void addProcess(Class<? extends Process> processClass) {
        ProcessCreator processCreator = new ProcessCreator(processClass);
        ProcessLayer processLayer = new ProcessLayer(processCreator, this);
        processLayerLinkedList.add(processLayer);
    }

    public void setup() {
        setupNextLayer(Collections.singletonList(baseUrl));
    }

    public void setupNextLayer(List<String> urls) {
        try {
            if (processLayerLinkedList.size() > 0) {
                ProcessLayer processLayer = processLayerLinkedList.removeFirst();
                if (processLayer != null)
                    processLayer.setUp(urls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    @Override
    public void onLayerExecuteFinished(ProcessLayer processLayer, DataManager dataManager) {

        if (dataListener != null) {
            dataListener.onReceivedData(dataManager);
        }

        if (dataManager.getNextLayerUrls().size() > 0)
            setupNextLayer(dataManager.getNextLayerUrls());
    }
}
