package org.wceng.zidian;

import com.google.gson.Gson;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

import java.util.*;

public class WordEntityDefaultProcess extends Process {

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
        List<HashMap<String, List<String>>> jieshi = null;


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

        try {
            jieshi = parseJieshi(document);
        } catch (Exception ignored) {
        }

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

    private List<HashMap<String, List<String>>> parseJieshi(Document document) throws Exception {

        List<HashMap<String, List<String>>> pinyinList = new ArrayList<>();

        Element e1 = document.getElementsByClass("zi_text_content").get(0);
        String text = e1.getElementsByTag("p").get(0).text();

        text = text.substring(text.indexOf("● ") + 2);
        String[] split1 = text.split("● ");

        for (int i = 0; i < split1.length; i++) {
            String s = split1[i];

            String pinyin = s.substring(0, text.indexOf("◎"));
            if (pinyin.contains("）")) {
                pinyin =pinyin.substring(pinyin.indexOf("）") + 1).trim().split(" ")[0];
            }else {
                pinyin = pinyin.substring(1).trim().split(" ")[0];
            }

            String[] jieshi = s.substring(text.indexOf("◎")).split("◎");

            List<String> jieshiList = new ArrayList<>();

            for (String s1 : jieshi){
                s1 = s1.trim();
                if(s1.length() != 0){
                    jieshiList.add(s1.replace("◎", ""));
                }
            }

            HashMap<String, List<String>> pinyin_jieshi = new LinkedHashMap<>();
            pinyin_jieshi.put(pinyin, jieshiList);
            pinyinList.add(pinyin_jieshi);
        }
        return pinyinList;
    }


}
