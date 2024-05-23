package org.wceng.util;

import de.l3s.boilerpipe.extractors.CommonExtractors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElementUtil {

    public static String absHref(Element element) throws Exception {
        return element.absUrl("href");
    }

    public static String parseText(Element element) throws Exception {
        String html = element.html();
        return CommonExtractors.KEEP_EVERYTHING_EXTRACTOR.getText(html);
    }

    public static String parseText(Elements elements) throws Exception {
        String html = elements.html();
        return CommonExtractors.KEEP_EVERYTHING_EXTRACTOR.getText(html);
    }
}
