package org.wceng.client.poems;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PoemUrlProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        Elements poetUrlElems = doc.select(".col-sm-12 .card-title a");

        for (Element poetUrlElem : poetUrlElems) {
            String poetUrl = ElementUtil.absHref(poetUrlElem);
            getBundler().putNextLayerUrls(poetUrl);
        }

        Element nextElem = doc.select(".pagination .page-item").last().select("a").first();
        if (nextElem.hasAttr("aria-label") && nextElem.attr("aria-label").equals("Next")) {
            String nextHref = ElementUtil.absHref(nextElem);
//            getBundler().putLoopLayerUrls(nextHref);
        }
    }
}
