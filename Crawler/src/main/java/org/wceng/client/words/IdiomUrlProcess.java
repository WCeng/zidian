package org.wceng.client.words;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class IdiomUrlProcess extends Process {
    @Override
    public void extractDocument(Document document) throws Exception {

        try {
            String curUrl = getUrl();
            Element parent = document.getElementById("pages");


            String currentPage;
            currentPage = !curUrl.contains("=") ? "1" : curUrl.substring(curUrl.lastIndexOf("=") + 1);


            String nextHref = ElementUtil.absHref(parent.select("a").last());

            String nextHrefPage = nextHref.substring(nextHref.indexOf("=") + 1);
            if (!currentPage.equals(nextHrefPage)) {
                getBundler().putLoopLayerUrls(nextHref);
            }
        } catch (Exception e) {
        }


        Element idiomsSpan = document.select(".common6").get(0);

        Elements lis = idiomsSpan.select("li");
        for (Element li : lis) {
            Element a = li.select("a").get(0);
            getBundler().putNextLayerUrls(ElementUtil.absHref(a));
        }
    }
}
