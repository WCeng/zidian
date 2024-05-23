package org.wceng.client.yilin;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class YilinSectionUrlProcess  extends Process {
    @Override
    public void extractDocument(Document document) throws Exception {
        String title = document.getElementsByTag("h1").text();
        Elements as = document.getElementsByClass("blkContainer").get(0).getElementsByTag("a");
        getBundler().putStringList(title);
        for (Element a : as){
            getBundler().putNextLayerUrls(ElementUtil.absHref(a));
        }

    }
}
