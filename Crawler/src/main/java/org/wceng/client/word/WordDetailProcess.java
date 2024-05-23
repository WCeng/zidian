package org.wceng.client.word;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordDetailProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        String word = "";
        List<String> phonicslist = new ArrayList<>();
        String traditional = "";
        String radical = "";
        List<HashMap<String, List<String>>> explanations = new ArrayList<>();
        List<String> englishlist = new ArrayList<>();

        Elements elements = doc.select(".cont_hzcx .list_tico li");
        for (int i = 0; i < elements.size(); i++) {
            if(i == 0){

            }
        }
    }
}
