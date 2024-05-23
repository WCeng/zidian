package org.wceng.listener;

import org.wceng.component.Bundler;
import org.wceng.component.Process;

import java.util.List;

@Deprecated
public class DataListener {

    public static class ProcessChecker {
        Class<? extends Process> processCLass;
        int index;

        public ProcessChecker(Class<? extends Process> processCLass, int index) {
            this.processCLass = processCLass;
            this.index = index;
        }

        public boolean is(Class<? extends Process> processCLass) {
            return this.processCLass == processCLass;
        }

        public boolean is(int index) {
            return this.index == index;
        }
    }

    public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {

    }


    public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {

    }

    public void afterCacheBundlers(ProcessChecker checker, List<Bundler> cachedBundlers) {

    }


}
