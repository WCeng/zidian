package org.wceng.client.antusheng;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class AntushengUrlProcess extends Process {
    @Override
    public void extractDocument(Document document) throws Exception {
        Elements spans = document.getElementsByClass("story_header");
        for (Element span : spans){
            Element a = span.getElementsByTag("a").get(0);
            getBundler().putStringList(a.text());
            getBundler().putNextLayerUrls(ElementUtil.absHref(a));
        }

        Element next = document.getElementsByClass("page-links").get(0).getElementsByClass("next").get(0).getElementsByTag("a").get(0);
        getBundler().putLoopLayerUrls(ElementUtil.absHref(next));
    }
}
