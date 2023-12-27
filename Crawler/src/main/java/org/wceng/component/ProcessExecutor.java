package org.wceng.component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessExecutor {

    private final ExecutorService executorService;

    public ProcessExecutor() {
        executorService = Executors.newFixedThreadPool(30);
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executorService.awaitTermination(timeout, unit);
    }

    public void execute(Process process) {
        executorService.submit(process);
    }


    public void shutdown() {
        executorService.shutdown();
    }
}
