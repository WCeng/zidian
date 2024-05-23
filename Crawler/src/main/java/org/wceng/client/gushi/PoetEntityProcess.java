package org.wceng.client.gushi;

import org.apache.xerces.impl.xpath.XPath;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PoetEntityProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {


        String title = doc.select("h1").get(0).text();

        String chaodai = doc.select(".author-simple-info a").get(0).text();
        String poet = doc.select(".author-simple-info a").get(1).text();
        String content = ElementUtil.parseText(doc.select(".shicicontent").get(0));


        getBundler().putHashMap("title", title);
        getBundler().putHashMap("chaodai", chaodai);
        getBundler().putHashMap("poet", poet);
        getBundler().putHashMap("content", content);
    }
}
