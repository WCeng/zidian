package org.wceng;

import com.google.gson.Gson;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Data;
import org.wceng.component.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordEntityProcess extends Process {

    @Override
    public void extractDocument(Document document) {
        Data data = new Data();

        String word = null;
        List<String> pinyin = null;
        List<String> zhuyin = null;
        String fanti = null;
        String jiegou = null;
        String bushou = null;
        String zaozifa = null;
        String bishun = null;
        List<String> jieshi = null;


        Elements elementsByClass = document.getElementsByClass("ml15");
        if (elementsByClass.size() > 0) {
            Element element = elementsByClass.get(0);
            word = element.text().substring(0, 1);
        }

        pinyin = parsePinyin(document);
        zhuyin = parseZhuyin(document);
        fanti = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(4).text()
                .substring(4);
        jiegou = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(5).text()
                .substring(5);
        bushou = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(6).text()
                .substring(5);
        zaozifa = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(7).text()
                .substring(4);
        bishun = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(5).text()
                .substring(3);

        jieshi = parseJieshi(document);

        data.put("word", word);
        data.put("pinyin", pinyin);
        data.put("zhuyin", zhuyin);
        data.put("fanti", fanti);
        data.put("jiegou", jiegou);
        data.put("bushou", bushou);
        data.put("zaozifa", zaozifa);
        data.put("bishun", bishun);
        data.put("jieshi", jieshi);

        getDataManager().putData(data);

        System.out.println(new Gson().toJson(data));
    }

    private List<String> parsePinyin(Document document) {
        String substring = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(0).text()
                .substring(3);
        String[] split = substring.trim().split(" ");
        return Arrays.asList(split);
    }

    private List<String> parseZhuyin(Document document) {
        String substring = document.getElementsByClass("list_tico").get(0).getElementsByTag("li").get(1).text()
                .substring(3);
        String[] split = substring.trim().split(" ");
        return Arrays.asList(split);
    }

    private List<String> parseJieshi(Document document) {

        Elements e0 = document.getElementsByClass("zi_text_content");
        if (e0.size() == 0) return null;

        Element e1 = e0.get(0);
        Element e2 = e1.getElementsByTag("p").get(0);
        String text = e2.text();
        int i = text.indexOf("◎ ");
        if (i > -1) {
            text = text.substring(i + 2);
            String[] split = text.split(" ◎ ");
            return new ArrayList<>(Arrays.asList(split));
        } else {
            return null;
        }

    }


}
