package org.wceng.base;

import org.jsoup.Connection;
import org.wceng.ZiDianClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class BaseExecutor {

    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    private final ZiDianClient client;

    public BaseExecutor(ZiDianClient client) {
        this.client = client;
    }

    public ZiDianClient getClient() {
        return client;
    }

    public void execute(ProcessChain processChain){
        processChain.get().iterator().forEachRemaining(baseProcess -> {

        });
    }

    private void handleProcess(BaseProcess baseProcess){
        List<String> list = service.submit(baseProcess).get();

        for (String s : list){

        }
    }


    public void shutdown() {
        service.shutdown();
    }

}
