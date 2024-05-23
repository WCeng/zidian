package org.wceng.client.words;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

public class IdiomEntityProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        String idiom = "";
        String pronunciation = "";
        String basicMeaning = "";
        String detailedMeaning = "";
        String storyOrigin = "";

        Elements pElems = doc.select("#watermark p");

        Element pronunciationElem = null;
        for (Element p : pElems) {
            if (p.text().startsWith("发音") && p.nextElementSibling().text().startsWith("基本")) {
                pronunciationElem = p;
            }
        }


        /**
         * idiom
         */
        try {
            Element idiomElem = pronunciationElem.previousElementSibling();
            String text = idiomElem.text();

            String tempIdiom = "";
            if (text.startsWith("成语（Idiom）：") || text.startsWith("成语（Idiom）:")) {
                tempIdiom = text.substring(10, text.length());
            } else {
                tempIdiom = text;
            }

            int removeIndex = tempIdiom.contains("（") ? tempIdiom.indexOf("（") : tempIdiom.contains("(") ? tempIdiom.indexOf("(") : -1;
            if (removeIndex != -1) {
                idiom = tempIdiom.substring(0, removeIndex);
            } else {
                idiom = tempIdiom;
            }
        } catch (Exception e) {
        }

        /**
         * pronunciation
         */
        try {
            String text = pronunciationElem.text();
            String tempPronunciation = "";
            if (text.startsWith("发音：") || text.startsWith("发音:")) {
                tempPronunciation = text.substring(3, text.length());
            } else {
                tempPronunciation = text.substring(18, text.length());
            }
            pronunciation = tempPronunciation;
        } catch (Exception e) {
        }

        /**
         * basicMeaning
         */
        try {
            Element basicMeaningElem = pronunciationElem.nextElementSibling();
            String text = basicMeaningElem.text();
            String temp = "";
            if (text.startsWith("基本含义：") || text.startsWith("基本含义:")) {
                temp = text.substring(5, text.length());
            } else {
                temp = text.substring(20, text.length());
            }
            basicMeaning = temp;
        } catch (Exception e) {
        }

        /**
         * detailedMeaning
         */
        try {
            Element detailedMeaningElem = pronunciationElem.nextElementSibling().nextElementSibling();
            String text = detailedMeaningElem.text();
            String temp = "";
            if (text.startsWith("详细解释:") || text.startsWith("详细解释：")) {
                temp = text.substring(5, text.length());
            } else {
                temp = text.substring(27, text.length());
            }
            detailedMeaning = temp;
        } catch (Exception e) {
        }


        /**
         * origin
         */
        try {
            Element storyOriginElem = pronunciationElem.nextElementSibling().nextElementSibling()
                    .nextElementSibling().nextElementSibling();
            String text = storyOriginElem.text();
            String temp = "";
            if (text.startsWith("故事起源：") || text.startsWith("故事起源:")) {
                temp = text.substring(5, text.length());
            } else {
                temp = text.substring(19, text.length());
            }
            storyOrigin = temp;
        } catch (Exception e) {
        }


        if (idiom.contains(pronunciation)) {
            idiom = idiom.substring(0, idiom.indexOf(pronunciation) - 1);
        }


        getBundler().putHashMap("idiom", idiom);
        getBundler().putHashMap("pronunciation", pronunciation);
        getBundler().putHashMap("basicMeaning", basicMeaning);
        getBundler().putHashMap("detailedMeaning", detailedMeaning);
        getBundler().putHashMap("storyOrigin", storyOrigin);

        getBundler().putStringList(idiom);
    }
}
