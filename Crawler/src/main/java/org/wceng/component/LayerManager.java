package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {

    private final List<ProcessLayer> processLayers;
    private final List<ProcessLayer> completedProcessLayers;

    public LayerManager() {
        processLayers = new ArrayList<>();
        completedProcessLayers = new ArrayList<>();
    }

    public void launchFirst(List<String> urls) {
        if (!processLayers.isEmpty()) {
            ProcessLayer processLayer = processLayers.get(0);
            launch(processLayer, urls);
        }
    }

    public void launchNext(ProcessLayer currentLayer, List<String> urls) {
        if (hasNextLayer(currentLayer)) {
            ProcessLayer nextLayer = nextLayer(currentLayer);
            launch(nextLayer, urls);
        }
    }

    public void launch(ProcessLayer layer, List<String> urls) {
        try {
            layer.launchWith(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addLayer(ProcessLayer processLayer) {
        processLayers.add(processLayer);
    }

    public void markCompleted(ProcessLayer processLayer) {
        processLayer.setCompleted(true);
        completedProcessLayers.add(processLayer);
    }

    public int index(ProcessLayer processLayer) {
        return processLayers.indexOf(processLayer);
    }

    public int count() {
        return processLayers.size();
    }

    public ProcessLayer get(int index) {
        return processLayers.get(index);
    }


    public boolean hasNextLayer(ProcessLayer currentLayer) {
        int index = index(currentLayer);
        return index < count() - 1;
    }

    public boolean hasPreLayer(ProcessLayer currentLayer) {
        int index = index(currentLayer);
        return index > 0;
    }

    public ProcessLayer preLayer(ProcessLayer currentLayer) {
        int index = index(currentLayer);
        return index > 0 ? processLayers.get(index - 1) : null;
    }

    public ProcessLayer nextLayer(ProcessLayer currentLayer) {
        int index = index(currentLayer);
        if (hasNextLayer(currentLayer)) {
            return get(index + 1);
        }
        return null;
    }

    public ProcessLayer getFirstRunningLayer() {
        for (ProcessLayer processLayer : processLayers) {
            if (!processLayer.isCompleted()) {
                return processLayer;
            }
        }
        return null;
    }

}

