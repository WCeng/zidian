package org.wceng.word;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.base.BaseProcess;

import java.util.ArrayList;
import java.util.List;


public class HtmlToPinyinUrlProcess extends BaseProcess {

    public HtmlToPinyinUrlProcess(String url) {
        super(url);
    }

    @Override
    protected List<String> doProcess(Document document) {

        List<String> list = new ArrayList<>();

        Elements elementsByClass = document.getElementsByClass("layui-card-body");
        for (Element e : elementsByClass){
            Elements as = e.getElementsByTag("a");
            for (Element a : as){
                String href = a.attr("href");
                list.add(href);
            }
        }
        return list;
    }

}
