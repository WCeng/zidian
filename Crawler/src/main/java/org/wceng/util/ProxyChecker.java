package org.wceng.util;

import org.jsoup.Jsoup;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProxyChecker {

    public static Proxy createHttpProxy(String address, int port) {
        InetSocketAddress proxyAddress = new InetSocketAddress(address, port);
        return new Proxy(Proxy.Type.HTTP, proxyAddress);
    }

    private ValidateCallback validateCallback;

    public void setValidateCallback(ValidateCallback validateCallback) {
        this.validateCallback = validateCallback;
        this.successProxyList = new ArrayList<>();
    }

    public interface ValidateCallback {
        void onSuccess(Proxy proxy);

        void onFail(Proxy proxy);
    }

    ExecutorService service = Executors.newFixedThreadPool(100);

    List<Proxy> successProxyList;

    public void validate(Proxy p) {
        service.submit(() -> {

            boolean isValidate = false;

            try {
                Jsoup.connect("https://myip.ipip.net/")
                        .timeout(5 * 1000)
                        .proxy(p)
                        .get();

                isValidate = true;
            } catch (Exception ignore) {
            }

            if (isValidate) {
                if (!successProxyList.contains(p)) {
                    successProxyList.add(p);
                    if (validateCallback != null) {
                        validateCallback.onSuccess(p);
                    }
                }
            } else {
                if (validateCallback != null) {
                    validateCallback.onFail(p);
                }
            }
        });
    }
}
