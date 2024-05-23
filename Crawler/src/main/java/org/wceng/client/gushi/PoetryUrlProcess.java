package org.wceng.client.gushi;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wceng.component.Process;

public class PoetryUrlProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {
        Elements as = doc.select(".simple-shiciqu a");
        for (Element a : as){
            String href = a.absUrl("href");
            getBundler().putNextLayerUrls(href);
        }

        Elements select = doc.select(".pagination li");
        for (int i = 0; i < select.size(); i++) {
            Element element = select.get(i);
            Elements elementsByClass = element.getElementsByClass("active");
            if(elementsByClass.size() > 0){
                Element next = select.get(i + 1).getElementsByTag("a").get(0);
                getBundler().putLoopLayerUrls(next.absUrl("href"));
                break;
            }
        }

    }
}
