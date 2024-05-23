package org.wceng.component;

public class Config {


    private int coreThreadCount;
    private int maxThreadCount;
    private boolean ignoreCertValida;
    private int connectTimeout;
    private String proxyAddress;
    private int proxyPort;
    private int connectTimeDelay;
    private int maxCachedBundlerCount;

    public int getConnectTimeDelay() {
        return connectTimeDelay;
    }

    public void setConnectTimeDelay(int connectTimeDelay) {
        this.connectTimeDelay = connectTimeDelay;
    }

    public Config(int coreThreadCount,
                  int maxThreadCount,
                  boolean ignoreCertValida,
                  int connectTimeout,
                  String proxyAddress,
                  int proxyPort,
                  int connectTimeDelay,
                  int maxCachedBundlerCount) {
        this.coreThreadCount = coreThreadCount;
        this.maxThreadCount = maxThreadCount;
        this.ignoreCertValida = ignoreCertValida;
        this.connectTimeout = connectTimeout;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.connectTimeDelay = connectTimeDelay;
        this.maxCachedBundlerCount = maxCachedBundlerCount;
    }

    public Config() {
        this(10, 15, false, 60 * 1000,
                null, -1, 0, 100);
    }


    public int getCoreThreadCount() {
        return coreThreadCount;
    }

    public void setCoreThreadCount(int coreThreadCount) {
        this.coreThreadCount = coreThreadCount;
    }

    public int getMaxThreadCount() {
        return maxThreadCount;
    }

    public void setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    public boolean isIgnoreCertValida() {
        return ignoreCertValida;
    }

    public void setIgnoreCertValida(boolean ignoreCertValida) {
        this.ignoreCertValida = ignoreCertValida;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxy(String proxyAddress, int proxyPort) {
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxCachedBundlerCount() {
        return maxCachedBundlerCount;
    }

    public void setMaxCachedBundlerCount(int maxCachedBundlerCount) {
        this.maxCachedBundlerCount = maxCachedBundlerCount;
    }
}
