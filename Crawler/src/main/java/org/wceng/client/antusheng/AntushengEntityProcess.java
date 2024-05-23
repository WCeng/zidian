package org.wceng.client.antusheng;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;

public class AntushengEntityProcess extends Process {
    @Override
    public void extractDocument(Document document) throws Exception {
        String title = document.getElementsByTag("h1").get(0).text();
        String content = document.getElementsByClass("article_content").get(0).text();

        getBundler().putHashMap("title", title);
        getBundler().putHashMap("content", content);
    }
}
