package org.wceng.base;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class BaseProcess implements Callable<List<String>> {

    private final String url;

    public BaseProcess(String url) {
        this.url = url;
    }

    @Override
    public List<String> call() throws Exception {
        Document document = Jsoup.connect(url)
                .timeout(2 * 60 * 1000)
                .get();
        return doProcess(document);
    }

    protected abstract List<String> doProcess(Document document);
}
