package org.wceng;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Data;
import org.wceng.component.Process;

import java.net.MalformedURLException;
import java.net.URL;


public class PinyinUrlProcess extends Process {

    @Override
    public void extractDocument(Document document) {
        Elements elementsByClass = document.getElementsByClass("layui-card-body");
        for (Element e : elementsByClass) {
            Elements as = e.getElementsByTag("a");
            for (Element a : as) {
                String href;
                String pinyin = a.text();

                try {
                    URL absoluteUrl = new URL(new URL(a.baseUri()), a.attr("href"));
                    href = absoluteUrl.toString();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }


                Data data = new Data();
                data.put("pinyin", pinyin);
                data.put("href", href);

                getDataManager().putData(data);
                getDataManager().putNextLayerUrls(href);
            }
        }
    }
}
