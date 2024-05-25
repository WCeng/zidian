package org.wceng.client.poems;

import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.component.ProcessLayer;
import org.wceng.component.LayerListener;

public class PoemsClient {

    public static ProcessChain poemsChain =
            new ProcessChain.Builder("https://www.xungushici.com/authors")
                    .setAllowFetchLayered(true)
                    .build();

    static {
        poemsChain.addProcess(PoetUrlProcess.class);
        poemsChain.addProcess(PoetDetailProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\poets.json");
        poemsChain.addProcess(PoemUrlProcess.class)
                .cacheListIn("C:\\Users\\王程程\\Desktop\\test\\poemUrls.json");
        poemsChain.addProcess(PoemDetailProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\poems.json");

        poemsChain.setLayerListener(new LayerListener() {
            @Override
            public void onLayerCompleted(LayerChecker checker, ProcessLayer.LayerInfo info) {

            }

            @Override
            public void onLayerRunning(LayerChecker checker, ProcessLayer.LayerInfo info) {
                if (checker.is(0)) {
                    System.out.println("0: " + info.currentProcessDocTitle);
                }
                if (checker.is(1)) {
                    System.out.println("1: " + info.currentProcessDocTitle);
                }
                if (checker.is(2)) {
                    System.out.println("2: " + info.currentProcessDocTitle);
                }
                if (checker.is(3))
                    System.out.println("3: " + info.currentProcessDocTitle);
//                    System.out.println(
//                            info.currentBundler.getHashMap().get("poet") + " : " +
//                                    info.currentBundler.getHashMap().get("title"));
            }

            @Override
            public void onLayerError(LayerChecker checker, String url, Exception e) {
            }

            @Override
            public void onConnectError(Exception e) {
            }
        });
    }

    public static void main(String[] args) {
        Crawler.getInstance().launch(poemsChain);
    }

}


