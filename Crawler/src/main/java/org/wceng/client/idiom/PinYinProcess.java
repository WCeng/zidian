package org.wceng.client.idiom;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PinYinProcess extends Process {


    @Override
    public void extractDocument(Document doc) throws Exception {
        Elements aElems = doc.select(".jsnr").first().select("a");
        for (Element aElem : aElems) {
            String href = ElementUtil.absHref(aElem);
            getBundler().putNextLayerUrls(href);
        }

//        getBundler().putNextLayerUrls(ElementUtil.abseHref(aElems.first()));
    }
}
