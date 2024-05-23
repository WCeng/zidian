package org.wceng.client.word;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PinYinProcess extends Process {

    static int index = 0;

    @Override
    public void extractDocument(Document doc) throws Exception {
        for (Element aElem : doc.select(".pylist li a")) {
            String href = ElementUtil.absHref(aElem);

            index++;
            if (index < 2)
                getBundler().putNextLayerUrls(href);
        }

    }
}
