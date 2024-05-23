package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

    private final List<Process> runningProcesses;
    private final List<Process> exceptProcesses;


    ProcessManager() {
        this.runningProcesses = new ArrayList<>();
        this.exceptProcesses = new ArrayList<>();
    }

    public void addProcess(Process process) {
        this.runningProcesses.add(process);
    }

    public void addProcesses(List<Process> processes) {
        for (Process p : processes) {
            addProcess(p);
        }
    }

    public void removeProcess(Process process) {
        this.runningProcesses.remove(process);
    }

    public boolean hasRunningProcess() {
        return !runningProcesses.isEmpty();
    }

    public void addExceptProcess(Process process) {
        this.exceptProcesses.add(process);
    }

    public long getExceptProcessCount() {
        return exceptProcesses.size();
    }
}

