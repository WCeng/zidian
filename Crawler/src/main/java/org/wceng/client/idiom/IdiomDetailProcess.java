package org.wceng.client.idiom;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

public class IdiomDetailProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        String idiom = "";
        String pronunciation = "";
        String basicMeaning = "";
        String storyOrigin = "";

        Elements pElems = doc.select(".layui-card-body p");
        for (int i = 0; i < pElems.size(); i++) {
            String text = pElems.get(i).text();
            if (i == 0) {
                try {

                    String tempIdiom = "";
                    if (text.startsWith("成语（Idiom）：") ||text.startsWith("成语（Idiom）:") ) {
                        tempIdiom = text.substring(10, text.length());
                    } else {
                        tempIdiom = text;
                        System.out.println(getUrl());
                    }

                    int removeIndex = tempIdiom.contains("（") ? tempIdiom.indexOf("（") : tempIdiom.contains("(") ? tempIdiom.indexOf("(") : -1;
                    if (removeIndex != -1) {
                        idiom = tempIdiom.substring(0, removeIndex);
                    } else {
                        idiom = tempIdiom;
                    }
                } catch (Exception e) {
                }

            }
            if (i == 1) {
                try {
                    pronunciation = text.substring(18, text.length());
                } catch (Exception e) {
                }
            }
            if (i == 2) {
                try {
                    basicMeaning = text.substring(20, text.length());
                } catch (Exception e) {
                }
            }
            if (i == 5) {
                try {
                    storyOrigin = text.substring(19, text.length());
                } catch (Exception e) {
                }
            }
        }

        getBundler().putHashMap("idiom", idiom);
        getBundler().putHashMap("pronunciation", pronunciation);
        getBundler().putHashMap("basicMeaning", basicMeaning);
        getBundler().putHashMap("storyOrigin", storyOrigin);
    }
}
