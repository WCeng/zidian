package org.wceng.client.idiom;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class IdiomProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        for (Element aElem : doc.select(".common6 a")) {
            String href = ElementUtil.absHref(aElem);
            getBundler().putNextLayerUrls(href);
        }

    }
}
