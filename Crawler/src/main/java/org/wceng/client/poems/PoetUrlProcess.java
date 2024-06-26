package org.wceng.client.poems;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PoetUrlProcess extends Process {

    static int i = 0;

    @Override
    public void extractDocument(Document doc) throws Exception {
        Element nextElem = doc.select(".page-link").last();
        if (nextElem.hasAttr("aria-label") && nextElem.attr("aria-label").equals("Next")) {
            String _nextHref = ElementUtil.absHref(nextElem);
            if (i < 2)
                getBundler().putLoopLayerUrls(_nextHref);
            i++;
        }

        Elements poetElems = doc.select(".ml-1 .card-title a:nth-of-type(2)");
        for (int i = 0; i < poetElems.size(); i++) {
            Element poetElem = poetElems.get(i);
            String _poetHref = ElementUtil.absHref(poetElem);
            getBundler().putNextLayerUrls(_poetHref);
        }
    }
}
