package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class ProcessCreator {

    private final ProcessLayer processLayer;

    public ProcessCreator(ProcessLayer processLayer) {
        this.processLayer = processLayer;
    }

    private Process createInstance()
            throws InstantiationException, IllegalAccessException {
        return processLayer.getProcessClass().newInstance();
    }

    public Process createProcess(String url) throws Exception {
        Process process = createInstance();
        Connector connector = new Connector(url, this);
        process.setConnector(connector);
        return process;
    }


    public List<Process> createProcesses(List<String> urls) throws Exception {
        List<Process> processes = new ArrayList<>();
        for (String url : urls) {
            processes.add(createProcess(url));
        }
        return processes;
    }

    public ProcessLayer getProcessLayer() {
        return processLayer;
    }
}
