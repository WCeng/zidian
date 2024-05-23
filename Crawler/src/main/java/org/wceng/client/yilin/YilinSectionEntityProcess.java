package org.wceng.client.yilin;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class YilinSectionEntityProcess extends Process {
    @Override
    public void extractDocument(Document document) throws Exception {

        String title = "";
        title = document.getElementsByClass("collectionContainer").get(0).getElementsByTag("h1").text();
        String content = ElementUtil.parseText(document.getElementsByClass("blkContainerSblkCon").get(0));

        getBundler().putHashMap("title", title);
        getBundler().putHashMap("content", content);

    }
}
