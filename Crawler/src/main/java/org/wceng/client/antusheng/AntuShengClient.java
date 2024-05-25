package org.wceng.client.antusheng;

import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.component.ProcessLayer;
import org.wceng.component.LayerListener;

public class AntuShengClient {
    public static void main(String[] args) {
        ProcessChain chain = new ProcessChain.Builder("https://www.qigushi.com/ats/")
                .setAllowFetchLayered(false)
                .setMaxCachedBundlerCount(0)
                .build();

        chain.addProcess(AntushengUrlProcess.class)
                .cacheListIn("C:\\Users\\王程程\\Desktop\\test\\antushengTitle.json");
        chain.addProcess(AntushengEntityProcess.class)
                .cacheMapIn("C:\\Users\\王程程\\Desktop\\test\\antusheng.json");

        chain.setLayerListener(new LayerListener() {
            @Override
            public void onLayerCompleted(LayerChecker checker, ProcessLayer.LayerInfo info) {

            }

            @Override
            public void onLayerRunning(LayerChecker checker, ProcessLayer.LayerInfo info) {
                if (checker.is(0)) {
                    System.out.println("0" + info.currentProcessDocTitle);
                }

                if (checker.is(1)) {
                    System.out.println("1" + info.currentProcessDocTitle);
                }
            }

            @Override
            public void onLayerError(LayerChecker checker, String url, Exception e) {

            }

            @Override
            public void onConnectError(Exception e) {

            }
        });

        Crawler.getInstance().launch(chain);
    }
}
