package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {

    private final List<ProcessLayer> processLayers;

    public LayerManager() {
        processLayers = new ArrayList<>();
    }

    public void addLayer(ProcessLayer processLayer) {
        processLayers.add(processLayer);
    }

    public int index(ProcessLayer processLayer) {
        return processLayers.indexOf(processLayer);
    }

    public int count() {
        return processLayers.size();
    }

    public List<ProcessLayer> getProcessLayers() {
        return processLayers;
    }

    public ProcessLayer get(int index) {
        return processLayers.get(index);
    }

}

