package org.wceng.word;

import javafx.beans.Observable;
import org.jsoup.nodes.Document;
import org.wceng.base.BaseProcess;

import java.util.List;
import java.util.Observer;

public class PinyinToWordUrlProcess extends BaseProcess{

    public PinyinToWordUrlProcess(String url) {
        super(url);
    }

    @Override
    protected List<String> doProcess(Document document) {
        return null;
    }
}