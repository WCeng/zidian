package org.wceng;

import org.wceng.component.ProcessChain;

import java.util.ArrayList;
import java.util.List;

public class Crawler {

    private final List<ProcessChain> processChains;

    public Crawler() {
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


