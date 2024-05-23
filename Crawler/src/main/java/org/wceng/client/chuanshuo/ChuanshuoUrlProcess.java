package org.wceng.client.chuanshuo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class ChuanshuoUrlProcess extends Process {

    @Override
    public void extractDocument(Document document) throws Exception {

        Elements spans = document.getElementsByClass("gs");
        for (Element span : spans) {
            Element a = span.getElementsByTag("a").get(0);
            String href = ElementUtil.absHref(a);
//            getBundler().putNextLayerUrls(href);
            getBundler().putStringList(a.text());
        }

        Elements pageSpan = document.getElementsByClass("ep-pages").get(0).getElementsByTag("a");
        for (Element a : pageSpan){
            if(a.text().equals("下一页")){
                getBundler().putLoopLayerUrls(ElementUtil.absHref(a));
            }
        }
    }
}
