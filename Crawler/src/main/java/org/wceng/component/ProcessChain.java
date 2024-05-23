package org.wceng.component;

import org.wceng.listener.LayerListener;

import java.util.Collections;

public class ProcessChain {
    private final LayerLauncher layerLauncher;
    private final ProcessExecutor processExecutor;
    private final LayerManager layerManager;
    private LayerListener layerListener;

    private final Builder builder;
    private final String baseUrl;
    private final boolean ignoreCertValida;
    private final int connectTimeout;
    private final String proxyAddress;
    private final int proxyPort;
    private final int connectTimeDelay;
    private final int maxCachedBundlerCount;

    public ProcessChain(Builder builder) {
        this.builder = builder;
        this.baseUrl = builder.baseUrl;
        this.ignoreCertValida = builder.ignoreCertValida;
        this.connectTimeDelay = builder.connectTimeDelay;
        this.maxCachedBundlerCount = builder.maxCachedBundlerCount;
        this.connectTimeout = builder.connectTimeout;
        this.proxyAddress = builder.proxyAddress;
        this.proxyPort = builder.proxyPort;

        this.layerLauncher = new LayerLauncher();
        this.processExecutor = new ProcessExecutor(this.builder.threadCount);
        this.layerManager = new LayerManager();
    }

    public int getConnectTimeDelay() {
        return connectTimeDelay;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getMaxCachedBundlerCount() {
        return maxCachedBundlerCount;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public boolean isIgnoreCertValida() {
        return ignoreCertValida;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static class Builder {
        private final String baseUrl;
        private int threadCount = 10;
        private boolean ignoreCertValida = false;
        private int connectTimeout = 2 * 60 * 1000;
        private String proxyAddress = null;
        private int proxyPort = -1;
        private int connectTimeDelay = 0;
        private int maxCachedBundlerCount = 100;

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        public Builder setIgnoreCertValidation(boolean ignoreCertValida) {
            this.ignoreCertValida = ignoreCertValida;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setProxy(String proxyAddress, int proxyPort) {
            this.proxyAddress = proxyAddress;
            this.proxyPort = proxyPort;
            return this;
        }

        public Builder setConnectTimeDelay(int connectTimeDelay) {
            this.connectTimeDelay = connectTimeDelay;
            return this;
        }

        public Builder setMaxCachedBundlerCount(int maxCachedBundlerCount) {
            this.maxCachedBundlerCount = maxCachedBundlerCount;
            return this;
        }

        public ProcessChain build() {
            return new ProcessChain(this);
        }
    }


    public BundlerBuffer addProcess(Class<? extends Process> processClass) {
        ProcessLayer processLayer = new ProcessLayer(processClass, this);
        layerManager.addLayer(processLayer);
        return processLayer.getBundlerBuffer();
    }

    void setup() {
        if (layerManager.count() > 0)
            layerLauncher.launch(getLayerManager().get(0), Collections.singletonList(baseUrl));
    }

    public void setLayerListener(LayerListener layerListener) {
        this.layerListener = layerListener;
    }

    LayerListener getLayerListener() {
        return layerListener;
    }

    LayerLauncher getLayerLauncher() {
        return layerLauncher;
    }

    ProcessExecutor getProcessExecutor() {
        return processExecutor;
    }

    LayerManager getLayerManager() {
        return layerManager;
    }


}
