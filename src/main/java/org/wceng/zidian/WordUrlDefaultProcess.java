package org.wceng.zidian;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

import java.net.MalformedURLException;
import java.net.URL;

public class WordUrlDefaultProcess extends Process {


    @Override
    public void extractDocument(Document document) {
        Elements elementsByClass = document.getElementsByClass("fontbox");
        for (Element a : elementsByClass) {
            String href = a.attr("href");
            String text = a.text();
            try {
                URL absoluteUrl = new URL(new URL(a.baseUri()), href);

                Data data = new Data();
                data.put("word", text);
                data.put("wordUrl", absoluteUrl.toString());

                getDataManager().putData(data);
                getDataManager().putNextLayerUrls(absoluteUrl.toString());

            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
