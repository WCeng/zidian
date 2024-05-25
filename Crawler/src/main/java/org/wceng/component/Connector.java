package org.wceng.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.wceng.util.SSLUtil;

import javax.net.ssl.SSLSocketFactory;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class Connector {

    private final String url;
    private final Proxy proxy;
    private final SSLSocketFactory sslSocketFactory;
    private final ProcessCreator processCreator;
    private final boolean isIgnoreCertValida;
    private final int connectTimeout;
    private final String proxyAddress;
    private final int proxyPort;
    private final int connectTimeDelay;

    public Connector(String url, ProcessCreator processCreator) {
        this.url = url;
        this.processCreator = processCreator;
        this.isIgnoreCertValida = this.processCreator.getProcessLayer().getChain().isIgnoreCertValida();
        this.connectTimeout = this.processCreator.getProcessLayer().getChain().getConnectTimeout();
        this.proxyAddress = this.processCreator.getProcessLayer().getChain().getProxyAddress();
        this.proxyPort = this.processCreator.getProcessLayer().getChain().getProxyPort();
        this.connectTimeDelay = this.processCreator.getProcessLayer().getChain().getConnectTimeDelay();
        if (isIgnoreCertValida) {
            sslSocketFactory = SSLUtil.ignoreCertValidation();
        } else {
            sslSocketFactory = SSLUtil.validateCert();
        }
        if (proxyAddress != null && proxyPort > 0) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
        } else {
            proxy = null;
        }

    }

    public Document connect() {
        Document doc = null;
        try {
            Thread.sleep(connectTimeDelay);
            doc = Jsoup.connect(url)
                    .timeout(connectTimeout)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .proxy(proxy)
                    .sslSocketFactory(sslSocketFactory)
                    .get();
        } catch (Exception e) {
            LayerListener listener = processCreator.getProcessLayer().getChain().getLayerListener();
            if (listener != null) {
                listener.onConnectError(new RuntimeException("connect error in " + url, e));
            }
        }
        return doc;
    }

    public String getUrl() {
        return url;
    }

    ProcessCreator getProcessCreator() {
        return processCreator;
    }
}
