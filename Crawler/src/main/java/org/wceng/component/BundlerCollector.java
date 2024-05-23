package org.wceng.component;

import org.wceng.listener.DataListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BundlerCollector {

    private final HashMap<Class<? extends Process>, List<Bundler>> map;
    private DataListener dataListener;
    private final ProcessChain chain;



    public BundlerCollector(ProcessChain chain) {
        this.chain = chain;
        this.map = new LinkedHashMap<>();
    }

    synchronized void collect(ProcessLayer processLayer, Bundler bundler) {
        Class<? extends Process> processClass = processLayer.getProcessClass();

        if (map.containsKey(processClass)) {
            map.get(processClass).add(bundler);
        } else {
            List<Bundler> bundlers = new ArrayList<>();
            bundlers.add(bundler);
            map.put(processClass, bundlers);
        }



        if (dataListener != null) {
            int index = chain.getLayerManager().index(processLayer);
            dataListener.afterProcessFetch(new DataListener.ProcessChecker(processClass, index), bundler);
            if (processLayer.isCompleted()) {
                dataListener.afterLayerFetch(new DataListener.ProcessChecker(processClass, index), map.get(processClass));
            }
        }
    }

    public void setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

}
