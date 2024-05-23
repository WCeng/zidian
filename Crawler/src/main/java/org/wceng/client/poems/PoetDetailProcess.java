package org.wceng.client.poems;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

public class PoetDetailProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        String name = doc.select("h1").text();
        String dynasty = "";
        String des = "";
        String imgUrl = "";
        try {
            String dynastyStr = doc.select(".ml-1").text();
            dynasty = dynastyStr.substring(1, dynastyStr.length() - 1);
            des = doc.select(".card-body").get(0).select("p").get(0).text();
            imgUrl = doc.select(".mr-3").get(0).attr("src");
        } catch (Exception e) {
        }


        getBundler().putHashMap("name", name);
        getBundler().putHashMap("dynasty", dynasty);
        getBundler().putHashMap("des", des);
        getBundler().putHashMap("imgUrl", imgUrl);

        Element element = doc.select(".mt-3").get(1).select("a").first();
        String poemsUrl = ElementUtil.absHref(element);
        getBundler().putNextLayerUrls(poemsUrl);
    }
}
