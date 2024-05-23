package org.wceng.component;

import java.util.ArrayList;
import java.util.List;

public class Crawler {

    private final List<ProcessChain> processChains;

    private static Crawler crawler;

    public synchronized static Crawler getInstance() {
        if(crawler == null) {
            crawler = new Crawler();
        }
        return crawler;
    }

    private Crawler() {
        processChains = new ArrayList<>();
    }

    public Crawler addChain(ProcessChain processChain) {
        processChains.add(processChain);
        return this;
    }

    public void setup() {
        for (ProcessChain chain : processChains) {
            chain.setup();
        }
    }

}


