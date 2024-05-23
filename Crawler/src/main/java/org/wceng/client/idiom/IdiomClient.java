package org.wceng.client.idiom;

import org.wceng.component.Bundler;
import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.listener.DataListener;
import org.wceng.util.BundlerUtil;
import org.wceng.util.FileUtil;

import java.io.File;
import java.util.List;

public class IdiomClient {

    public static void main(String[] args) {
        Crawler crawler = Crawler.getInstance();

        ProcessChain chain = new ProcessChain("https://www.chazidian.com/cy/");

        chain.addProcess(PinYinProcess.class);
        chain.addProcess(PinYinChildProcess.class);
        chain.addProcess(IdiomProcess.class);
        chain.addProcess(IdiomDetailProcess.class);

        chain.setDataListener(new DataListener() {
            @Override
            public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
                if (checker.is(IdiomDetailProcess.class)) {
                    String content = BundlerUtil.convertMapToJson(bundlers);
                    FileUtil.saveStringToFile(content, new File("C:\\Users\\王程程\\Desktop\\test\\idiom.json"));
                }
            }

            @Override
            public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
                if (checker.is(IdiomDetailProcess.class)) {
                    System.out.println(BundlerUtil.convertMapToJson(bundler));
                }
            }
        });

        crawler.addChain(chain).setup();
    }
}
