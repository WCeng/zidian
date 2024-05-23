package org.wceng.client.yilin;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class YilinBookUrlProcess extends Process {
    @Override
    public void extractDocument(Document document) throws Exception {
        Elements spans = document.getElementsByClass("time");
        for (int i = 0; i < spans.size(); i++) {
            Element span = spans.get(i);
            Element a = span.getElementsByTag("a").get(0);
            if (i > 200 && i < 210)
                getBundler().putNextLayerUrls(a.absUrl("href"));
        }


    }
}
