package org.wceng.gushi;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

import java.net.MalformedURLException;
import java.net.URL;

public class PoetUrlProcess extends Process {

    @Override
    public void extractDocument(Document document) {
        Elements es1 = document.getElementsByClass("shici-pic-box");

        String nextPage =null;
        try {
            nextPage = parseNextPageHref(document);
        } catch (Exception e) {

        }


        for (Element e1 : es1) {
            Element a = e1.getElementsByTag("p").get(1).getElementsByTag("a").get(0);

            try {
                String href = new URL(new URL(a.baseUri()), a.attr("href")).toString();

                Data data = new Data();
                data.put("url", href);
                getDataManager().putData(data);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(nextPage);
        getDataManager().putNeedLoopUrls(nextPage);
    }

    private String parseNextPageHref(Document document) throws Exception{
        Element a = document.getElementsByClass("amore").get(0);
        return new URL(new URL(a.baseUri()), a.attr("href")).toString();
    }

}
