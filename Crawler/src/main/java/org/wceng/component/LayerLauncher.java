package org.wceng.component;

import java.util.Collections;
import java.util.List;

public class LayerLauncher {

    private final ProcessChain processChain;

    public LayerLauncher(ProcessChain processChain) {
        this.processChain = processChain;
    }

    public void launch(ProcessLayer layer, List<String> urls) {
        try {
            layer.fetch(urls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
