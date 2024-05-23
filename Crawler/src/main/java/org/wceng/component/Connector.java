package org.wceng.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.SSLSocketFactory;
import java.net.Proxy;

public class Connector {

    String url;
    int timeout;
    Proxy proxy;
    SSLSocketFactory sslSocketFactory;
    int processConnectTime;

    public Connector(String url, int timeout, Proxy proxy, SSLSocketFactory sslSocketFactory, int processConnectTime) {
        this.url = url;
        this.timeout = timeout;
        this.proxy = proxy;
        this.sslSocketFactory = sslSocketFactory;
        this.processConnectTime = processConnectTime;
    }

    public Document connect() throws Exception {
        Thread.sleep(processConnectTime);
        return Jsoup.connect(url)
                .timeout(timeout) // 设置连接超时时间为30秒钟
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .proxy(proxy)
                .sslSocketFactory(sslSocketFactory)
                .get();
    }

    public String getUrl() {
        return url;
    }
}
