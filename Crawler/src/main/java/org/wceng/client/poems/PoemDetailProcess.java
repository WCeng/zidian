package org.wceng.client.poems;

import com.google.gson.Gson;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;
import org.wceng.util.ElementUtil;

import java.util.ArrayList;
import java.util.List;

public class PoemDetailProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        String title = doc.select("h1").first().text();

        String dynasty = "";
        String poet = "";
        String content = "";
        String translation = "";
        String annotation = "";
        String background = "";
        String appreciation = "";
        List<String> labels = new ArrayList<>();
        try {
            dynasty = doc.select("h1").next().first().select("a").first().text();
            poet = doc.select("h1").next().first().select("a").last().text();
            Element contentElem = doc.select("h1").next().next().select("div").first();
            content = ElementUtil.parseText(contentElem);

        } catch (Exception e) {
        }

        try {
            for (Element element : doc.select(".badge")) {
                labels.add(element.text());
            }
        } catch (Exception e) {
        }

        try {
            Elements elements = doc.select(".card-header");
            for (Element element : elements) {
                switch (element.text()) {
                    case "译文及注释":
                        Elements transAndAnnoElems = element.nextElementSiblings().select("p");
                        List<Element> transList = new ArrayList<>();
                        List<Element> annoList = new ArrayList<>();
                        int i = 0;
                        for (Element p : transAndAnnoElems) {

                            if (p.text().equals("注释")) {
                                i++;
                            }

                            if (p.text().equals("注释") || p.text().equals("译文"))
                                continue;

                            if (i > 0) {
                                annoList.add(p);
                            } else {
                                transList.add(p);
                            }
                        }

                        translation = ElementUtil.parseText(new Elements(transList));
                        annotation = ElementUtil.parseText(new Elements(annoList));
                        break;
                    case "创作背景":
                        Element backgroundElem = element.nextElementSibling();
                        if (backgroundElem != null) {
                            background = ElementUtil.parseText(backgroundElem);
                        }
                        break;
                    case "赏析":
                        Element appreciationElem = element.nextElementSibling();
                        if (appreciationElem != null) {
                            appreciation = ElementUtil.parseText(appreciationElem);
                        }
                        break;
                }
            }
        } catch (Exception e) {
        }

        getBundler().putHashMap("title", title);
        getBundler().putHashMap("dynasty", dynasty);
        getBundler().putHashMap("poet", poet);
        getBundler().putHashMap("content", content);
        getBundler().putHashMap("translation", translation);
        getBundler().putHashMap("annotation", annotation);
        getBundler().putHashMap("background", background);
        getBundler().putHashMap("appreciation", appreciation);
        getBundler().putHashMap("labels", labels);
    }
}
