package org.wceng.component;

public class ProcessCreator {

    Class<? extends Process> processClass;

    public ProcessCreator(Class<? extends Process> processClass) {
        this.processClass = processClass;
    }

    public Process create()
            throws InstantiationException, IllegalAccessException {
        return processClass.newInstance();
    }

    public Class<? extends Process> getProcessClass() {
        return processClass;
    }
}
