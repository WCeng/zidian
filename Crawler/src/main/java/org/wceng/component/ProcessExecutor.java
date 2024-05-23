package org.wceng.component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProcessExecutor extends ThreadPoolExecutor {

    private static final int DEFAULT_CORE_POOL_SIZE = 10;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 20;
    private static final long DEFAULT_KEEP_ALIVE_TIME = 60L;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private final HashMap<Class<? extends Process>, ProcessFinishedListener> hashMap;

    public interface ProcessFinishedListener {
        void onProcessFinished(Process process);
    }

    public ProcessExecutor() {
        this(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAXIMUM_POOL_SIZE, DEFAULT_KEEP_ALIVE_TIME, DEFAULT_TIME_UNIT, new LinkedBlockingQueue<>());
    }

    public ProcessExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.hashMap = new HashMap<>();
        allowCoreThreadTimeOut(true);
        setKeepAliveTime(1, TimeUnit.SECONDS);
    }

    public void executeProcesses(List<Process> processes) {
        for (Process p : processes) {
            executeProcess(p);
        }
    }

    public void executeProcess(Process process) {
        execute(process);
    }

    public void addProcessFinishedListener(Class<? extends Process> processClass, ProcessFinishedListener processFinishedListener) {
        hashMap.put(processClass, processFinishedListener);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        notify((Process) r, t);
    }

    private void notify(Process p, Throwable t) {
        if (hashMap.containsKey(p.getClass())) {
            hashMap.get(p.getClass()).onProcessFinished(p);
        }
    }

}
