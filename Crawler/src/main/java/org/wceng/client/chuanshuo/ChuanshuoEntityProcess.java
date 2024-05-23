package org.wceng.client.chuanshuo;

import org.jsoup.nodes.Document;
import org.wceng.component.Process;

public class ChuanshuoEntityProcess extends Process {

    @Override
    public void extractDocument(Document document) throws Exception {

        String title = null;
        String content = null;

        title = document.getElementsByClass("gushi").get(0).getElementsByTag("h1").text();
        content = document.getElementsByClass("gushi").get(0).getElementById("zzzxcwqsdas").text();

        getBundler().putHashMap("title", title);
        getBundler().putHashMap("content", content);
    }
}
