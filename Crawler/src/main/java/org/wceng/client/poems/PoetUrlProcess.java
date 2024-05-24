package org.wceng.client.poems;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PoetUrlProcess extends Process {
    static int index = 0;

    @Override
    public void extractDocument(Document doc) throws Exception {
        Element nextElem = doc.select(".page-link").last();
        if (nextElem.hasAttr("aria-label") && nextElem.attr("aria-label").equals("Next")) {
            String _nextHref = ElementUtil.absHref(nextElem);
            getBundler().putLoopLayerUrls(_nextHref);
        }

        Elements poetElems = doc.select(".ml-1 .card-title a:nth-of-type(2)");
        for (Element poetElem : poetElems) {
            String _poetHref = ElementUtil.absHref(poetElem);
            getBundler().putNextLayerUrls(_poetHref);
        }
    }
}
