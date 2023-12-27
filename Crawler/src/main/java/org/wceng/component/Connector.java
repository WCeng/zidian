package org.wceng.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Connector {

    String url;

    public Connector(String url) {
        this.url = url;
    }

    public Document connect() throws IOException {
        return Jsoup.connect(url).timeout(30 * 1000).get();
    }

    public String getUrl() {
        return url;
    }
}
