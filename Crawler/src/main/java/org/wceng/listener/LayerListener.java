package org.wceng.listener;

import org.wceng.component.DataManager;
import org.wceng.component.ProcessLayer;

public interface LayerListener {

    void onLayerExecuteFinished(ProcessLayer processLayer, DataManager dataManager);
}
