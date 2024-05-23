package org.wceng.client.word;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class WordsProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        for (Element aElem : doc.select(".fontbox")) {
            getBundler().putNextLayerUrls(ElementUtil.absHref(aElem));
            System.out.println(aElem.text());
        }

    }
}
