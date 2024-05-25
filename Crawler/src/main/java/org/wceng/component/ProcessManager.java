package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

    private final List<Process> runningProcesses;
    private final List<Process> exceptProcesses;
    private long processCount;
    private ProcessLayer processLayer;


    public ProcessManager(ProcessLayer processLayer) {
        this.processLayer = processLayer;
        this.processCount = 0;
        this.runningProcesses = new ArrayList<>();
        this.exceptProcesses = new ArrayList<>();
    }

    public void addRunningProcess(Process process) {
        this.runningProcesses.add(process);
        this.processCount++;
    }

    public void addRunningProcesses(List<Process> processes) {
        for (Process p : processes) {
            addRunningProcess(p);
        }
    }

    //
    public void removeRunningProcess(Process process) {
        this.runningProcesses.remove(process);
    }

    //
    public boolean hasRunningProcess() {
        return !runningProcesses.isEmpty();
    }
//
//    public void addExceptProcess(Process process) {
//        this.exceptProcesses.add(process);
//    }
//
//    public long getExceptProcessCount() {
//        return exceptProcesses.size();
//    }
//
//    public long getProcessCount() {
//        return processCount;
//    }

//    public void listenProcessCompleted(Process process) {
//        runningProcesses.remove(process);
//    }
}

