package org.wceng.client.poems;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wceng.component.Process;

public class LabelProcess extends Process {
    @Override
    public void extractDocument(Document doc) throws Exception {

        for (Element element : doc.select(".badge")) {
            if (element != null && !element.text().isEmpty()) {
                getBundler().putStringList(element.text());
            }
        }

    }
}
