package org.wceng.util;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class SSLUtil {
    // 忽略证书验证
    public static SSLSocketFactory ignoreCertValidation() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            sslContext.init(null, trustAllCerts, null);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }

        return sslContext.getSocketFactory();
    }

    // 恢复默认设置
    public static SSLSocketFactory validateCert() {
        return (SSLSocketFactory) SSLSocketFactory.getDefault();
    }

}
