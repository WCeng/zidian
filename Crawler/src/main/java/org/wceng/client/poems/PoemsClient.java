package org.wceng.client.poems;

import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.component.ProcessLayer;
import org.wceng.listener.LayerListener;

public class PoemsClient {

    public static ProcessChain poemsChain =
            new ProcessChain.Builder("https://www.xungushici.com/authors")
                    .build();

    static {
        poemsChain.addProcess(PoetUrlProcess.class);
        poemsChain.addProcess(PoetDetailProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\poets.json");
        poemsChain.addProcess(PoemUrlProcess.class);
        poemsChain.addProcess(PoemDetailProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\poems.json");
        Crawler.getInstance().launch(poemsChain);

        poemsChain.setLayerListener(new LayerListener() {
            @Override
            public void onLayerCompleted(LayerChecker checker, ProcessLayer.LayerInfo info) {

            }

            @Override
            public void onLayerRunning(LayerChecker checker, ProcessLayer.LayerInfo info) {
                if (checker.is(3))
                    System.out.println(
                            info.currentBundler.getHashMap().get("poet") + " : " +
                                    info.currentBundler.getHashMap().get("title"));
            }

            @Override
            public void onLayerError(LayerChecker checker, String url, Exception e) {
            }

            @Override
            public void onConnectError(Exception e) {
            }
        });
    }

}


