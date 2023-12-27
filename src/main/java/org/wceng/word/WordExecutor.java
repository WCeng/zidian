package org.wceng.word;

import org.wceng.ZiDianClient;
import org.wceng.base.BaseExecutor;

public class WordExecutor extends BaseExecutor {

    public static final String HTML_TO_PINYIN_URL = "https://www.chazidian.com/zidian/#zimulista";

    private HtmlToPinyinUrlProcess htmlToPinyinUrlProcess;

    public WordExecutor(ZiDianClient client) {
        super(client);
        htmlToPinyinUrlProcess = new HtmlToPinyinUrlProcess(HTML_TO_PINYIN_URL);


    }



}
