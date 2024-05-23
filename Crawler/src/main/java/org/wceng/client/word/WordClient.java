package org.wceng.client.word;

import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;

public class WordClient {

    public static void main(String[] args) {
        Crawler crawler = Crawler.getInstance();

        ProcessChain chain = new ProcessChain("https://www.chazidian.com/zidian/#zimulista");
        chain.addProcess(PinYinProcess.class);
        chain.addProcess(WordsProcess.class);

        crawler.addChain(chain).setup();
    }
}
