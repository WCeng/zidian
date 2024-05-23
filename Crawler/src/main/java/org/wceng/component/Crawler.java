package org.wceng.component;

public class Crawler {
    private static Crawler crawler;

    public synchronized static Crawler getInstance() {
        if (crawler == null) {
            crawler = new Crawler();
        }
        return crawler;
    }

    private Crawler() {
    }

    public void launch(ProcessChain... processChains) {
        for (ProcessChain chain : processChains) {
            chain.setup();
        }
    }
}


