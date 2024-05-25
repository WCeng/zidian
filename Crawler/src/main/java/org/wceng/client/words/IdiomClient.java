package org.wceng.client.words;

import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.component.ProcessLayer;
import org.wceng.component.LayerListener;

public class IdiomClient {

    public static void main(String[] args) {
        ProcessChain chain = new ProcessChain.Builder("https://www.chazidian.com/ci_pinyin/#a")
                .setThreadCount(70)
                .build();

        chain.addProcess(PinyinUrlProcess.class);
        chain.addProcess(IdiomUrlProcess.class)
                .cacheListIn("C:\\Users\\王程程\\Desktop\\test\\_idiom.json");
        chain.addProcess(IdiomEntityProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\idiom.json");
        chain.setLayerListener(new LayerListener() {
            @Override
            public void onLayerCompleted(LayerChecker checker, ProcessLayer.LayerInfo info) {
//                if (checker.is(2)) {
//                    System.out.println("error process num: " + info.exceptionProcessCount);
//                    System.out.println("完成");
//                }
            }

            @Override
            public void onLayerRunning(LayerChecker checker, ProcessLayer.LayerInfo info) {
                if (checker.is(0)) {
                    System.out.println("0:  " + info.currentProcessDocTitle);
                }
                if (checker.is(1)) {
                    System.out.println("1:  " + info.cachedBundlerCount);
                }
                if (checker.is(2)) {
                    System.out.println("2:  " + info.currentProcessDocTitle);
                }

//                if (checker.is(2)) {
//                    System.out.println(info.currentBundler.getHashMap());
//                }
            }

            @Override
            public void onLayerError(LayerChecker checker, String url, Exception e) {
//                if (checker.is(2)) {
//                    System.err.println(e.getMessage() + ":" + url);
//                }
            }

            @Override
            public void onConnectError(Exception e) {
                e.printStackTrace();
            }
        });


        Crawler.getInstance().launch(chain);
    }
}
