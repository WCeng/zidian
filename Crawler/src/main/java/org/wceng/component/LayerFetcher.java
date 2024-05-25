package org.wceng.component;

import java.util.List;

public interface LayerFetcher {

    ProcessLayer getProcessLayer();

    void onListenProcessCompleted(Process process);

    void fetch(List<String> urls);
}
