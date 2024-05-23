package org.wceng.client.words;

import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.component.ProcessLayer;
import org.wceng.listener.LayerListener;

public class IdiomClient {

    public static void main(String[] args) {
        ProcessChain chain = new ProcessChain("https://www.chazidian.com/ci_pinyin/#a");
        chain.addProcess(PinyinUrlProcess.class);
        chain.addProcess(IdiomUrlProcess.class);
        chain.addProcess(IdiomEntityProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\idiom.json");

        chain.getConfig().setCoreThreadCount(20);
        chain.getConfig().setMaxThreadCount(35);
        chain.getConfig().setMaxCachedBundlerCount(300);
        chain.getConfig().setConnectTimeout(5 * 60 * 1000);

        chain.setLayerListener(new LayerListener() {
            @Override
            public void onLayerCompleted(LayerChecker checker, ProcessLayer.LayerInfo info) {
                if (checker.is(2)) {
                    System.out.println(info.exceptionProcessCount);
                    System.out.println("完成");
                }
            }

            @Override
            public void onLayerRunning(LayerChecker checker, ProcessLayer.LayerInfo info) {
                if (checker.is(2)) {
                    System.out.println(info.currentBundler.getHashMap());
                }
            }

            @Override
            public void onLayerError(LayerChecker checker, String url, Exception e) {
                if (checker.is(2)) {
                    System.err.println(e.getMessage() + ":" + url);
                }
            }
        });
        Crawler.getInstance().addChain(chain).setup();
    }
}
