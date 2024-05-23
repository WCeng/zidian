package org.wceng.listener;

import org.wceng.component.Process;
import org.wceng.component.ProcessLayer;

public interface LayerListener {

    void onLayerCompleted(LayerChecker checker, ProcessLayer.LayerInfo info);

    void onLayerRunning(LayerChecker checker, ProcessLayer.LayerInfo info);

    void onLayerError(LayerChecker checker, String url, Exception e);

    class LayerChecker {
        Class<? extends Process> processCLass;
        int index;

        public LayerChecker(Class<? extends Process> processCLass, int index) {
            this.processCLass = processCLass;
            this.index = index;
        }

        public boolean is(Class<? extends Process> processCLass) {
            return this.processCLass == processCLass;
        }

        public boolean is(int index) {
            return this.index == index;
        }
    }

}
