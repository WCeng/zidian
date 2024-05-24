package org.wceng.component;

import org.wceng.util.BundlerUtil;
import org.wceng.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BundlerBuffer {

    private final int maxBundlerCacheCount;
    private final ProcessLayer layer;
    private final List<Bundler> waitCacheBundlers;
    private Bundler currentBundler;
    private long totalBundlerCachedCount;
    private final OutFileOperator fileOperator;
    private boolean needMapCache;
    private boolean needListCache;

    public BundlerBuffer(ProcessLayer layer) {
        this.layer = layer;
        this.waitCacheBundlers = new ArrayList<>();
        this.totalBundlerCachedCount = 0;
        this.fileOperator = new OutFileOperator();
        this.maxBundlerCacheCount = this.layer.getChain().getMaxCachedBundlerCount();
        this.needMapCache = false;
        this.needListCache = false;
    }

    public BundlerBuffer cacheMapIn(String path) {
        if (!path.isEmpty() && fileOperator.initMapOutFile(path)) {
            needMapCache = true;
        } else {
            System.err.println(path + "is not exist");
        }
        return this;
    }

    public BundlerBuffer cacheListIn(String path) {
        if (!path.isEmpty() && fileOperator.initListOutFile(path)) {
            needListCache = true;
        } else {
            System.err.println("path: " + path + " is not exist");
        }
        return this;
    }

    void cache(Bundler bundler) {
        if (!needMapCache && !needListCache) return;

        currentBundler = bundler;
        waitCacheBundlers.add(bundler);
        totalBundlerCachedCount++;

        if (layer.isCompleted()) {
            fileOperator.pushToFile(true);
        } else if (waitCacheBundlers.size() >= maxBundlerCacheCount) {
            fileOperator.pushToFile(false);
        }
    }


    private final class OutFileOperator {
        private final static String prefix = "[";
        private final static String suffix = "]";
        private final static String sign = ",";

        private File mapOutFile = null;
        private File listOutFile = null;
        private boolean isMapFirstPush = true;
        private boolean isListFirstPush = true;

        private boolean initMapOutFile(String path) {
            mapOutFile = new File(path);
            return initFile(mapOutFile);
        }

        private boolean initListOutFile(String path) {
            listOutFile = new File(path);
            return initFile(listOutFile);
        }

        private boolean initFile(File file) {
            if (file.exists() && file.delete()) {
                return true;
            }

            try {
                if (!file.exists() && file.createNewFile()) {
                    return true;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }

            return false;
        }

        private void pushToFile(boolean isCompleted) {
            if (needMapCache) {
                String mapStr = BundlerUtil.convertMapToJson(waitCacheBundlers);
                composeContentTo(mapOutFile, mapStr, isCompleted, isMapFirstPush);
                isMapFirstPush = false;
            }
            if (needListCache) {
                String listStr = BundlerUtil.convertListToJson(waitCacheBundlers);
                composeContentTo(listOutFile, listStr, isCompleted, isListFirstPush);
                isListFirstPush = false;
            }

            waitCacheBundlers.clear();
        }

        private void composeContentTo(File outFile, String s, boolean isCompleted, boolean isFirstPush) {
            s = s.substring(1, s.length() - 1);

            if (isFirstPush) {
                if (isCompleted) {
                    s = prefix + s + suffix;
                } else {
                    s = prefix + s;
                }
            } else {
                if (isCompleted) {
                    s = sign + s + suffix;
                } else {
                    s = sign + s;
                }
            }

            FileUtil.appendStringToFile(s, outFile);
        }
    }

    long getTotalBundlerCachedCount() {
        return totalBundlerCachedCount;
    }

    Bundler getCurrentBundler() {
        return currentBundler;
    }
}