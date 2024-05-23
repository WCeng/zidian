package org.wceng.client.gushi;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

public class PoetUrlProcess extends Process {

    @Override
    public void extractDocument(Document document) throws Exception {
        Elements as = document.select(".main-data li a");

        String href = as.get(0).absUrl("href");
        getBundler().putNextLayerUrls(href);
    }
}
