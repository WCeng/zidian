package org.wceng.client.words;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PinyinUrlProcess extends Process {


    @Override
    public void extractDocument(Document document) throws Exception {
        Elements zimuSpans = document.select(".pyjs_c a");

        for (int i = 0; i < zimuSpans.size(); i++) {
            Element aElem = zimuSpans.get(i);
            String href = ElementUtil.absHref(aElem);
            if (i > 0) {
                if (i < 3)
                    getBundler().putNextLayerUrls(href);
            } else {
                getBundler().putNextLayerUrls("https://www.chazidian.com/ci_a_27/");
            }
        }
    }
}
