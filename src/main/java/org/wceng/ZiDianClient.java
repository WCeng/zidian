package org.wceng;

import org.wceng.word.WordExecutor;

public class ZiDianClient {

    WordExecutor wordExecutor;

    public ZiDianClient() {
        wordExecutor = new WordExecutor(this);
    }

    public WordExecutor getWordExecutor() {
        return wordExecutor;
    }

    public void writeMessage(String msg){
        System.out.println(msg);
    }
}
