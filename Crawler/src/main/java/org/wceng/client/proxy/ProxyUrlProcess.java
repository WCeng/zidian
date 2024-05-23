package org.wceng.client.proxy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

public class ProxyUrlProcess extends Process {

    @Override
    public void extractDocument(Document doc) throws Exception {
        Elements spans = doc.select("tbody tr");
        for (Element span : spans) {
            String ip = span.select("td").get(0).text();
            String port = span.select("td").get(1).text();
            getBundler().putStringList(ip + ":" + port);
        }

        String href = doc.select(".layui-laypage-next").get(0).absUrl("href");
        if (spans.size() > 0)
            getBundler().putLoopLayerUrls(href);
    }
}
