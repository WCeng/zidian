package org.wceng.component;

import java.util.List;

public class LayerLauncher {

    public void launch(ProcessLayer layer, List<String> urls) {
        try {
            layer.fetch(urls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
