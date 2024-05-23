package org.wceng.component;

import org.wceng.listener.DataListener;
import org.wceng.listener.LayerListener;
import org.wceng.util.ProxyChecker;
import org.wceng.util.SSLUtil;

import java.util.Collections;
import java.util.List;

public class ProcessChain {
    private final LayerLauncher layerLauncher;
    private final BundlerCollector bundlerCollector;
    private final ProcessExecutor processExecutor;
    private final LayerManager layerManager;
    private final Config config;
    private final String baseUrl;
    private LayerListener layerListener;

    public ProcessChain(String baseUrl) {
        this.baseUrl = baseUrl;
        this.layerLauncher = new LayerLauncher(this);
        this.bundlerCollector = new BundlerCollector(this);
        this.processExecutor = new ProcessExecutor();
        this.layerManager = new LayerManager();
        this.config = new Config();
    }

    public BundlerBuffer addProcess(Class<? extends Process> processClass) {
        ProcessLayer processLayer = new ProcessLayer(processClass, this);
        layerManager.addLayer(processLayer);
        return processLayer.getBundlerBuffer();
    }

    void setup() {
        applyConfig(getConfig());

        if (layerManager.count() > 0)
            layerLauncher.launch(getLayerManager().get(0), Collections.singletonList(getBaseUrl()));
    }

    private void applyConfig(Config config) {
        processExecutor.setCorePoolSize(config.getCoreThreadCount());

        processExecutor.setMaximumPoolSize(config.getMaxThreadCount());

        List<ProcessLayer> processLayers = layerManager.getProcessLayers();
        for (ProcessLayer layer : processLayers) {
            layer.getBundlerBuffer().setMaxBundlerCacheCount(config.getMaxCachedBundlerCount());

            ProcessCreator processCreator = layer.getProcessCreator();
            processCreator.setConnectTimeout(config.getConnectTimeout());
            processCreator.setProcessConnectTime(config.getConnectTimeDelay());
            if (config.getProxyAddress() != null && config.getProxyPort() != -1)
                layer.getProcessCreator().setProxy(ProxyChecker.createHttpProxy(config.getProxyAddress(), config.getProxyPort()));
            if (config.isIgnoreCertValida()) {
                processCreator.setSslSocketFactory(SSLUtil.ignoreCertValidation());
            } else {
                processCreator.setSslSocketFactory(SSLUtil.validateCert());
            }
        }
    }

    public void setLayerListener(LayerListener layerListener) {
        this.layerListener = layerListener;
    }

    LayerListener getLayerListener() {
        return layerListener;
    }

    @Deprecated
    public void setDataListener(DataListener dataListener) {
        this.bundlerCollector.setDataListener(dataListener);
    }

    LayerLauncher getLayerLauncher() {
        return layerLauncher;
    }

    BundlerCollector getBundlerCollector() {
        return bundlerCollector;
    }

    ProcessExecutor getProcessExecutor() {
        return processExecutor;
    }

    LayerManager getLayerManager() {
        return layerManager;
    }

    String getBaseUrl() {
        return baseUrl;
    }

    public Config getConfig() {
        return config;
    }

}
