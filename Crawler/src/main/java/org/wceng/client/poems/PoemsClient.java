package org.wceng.client.poems;

import org.wceng.component.Bundler;
import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.listener.DataListener;
import org.wceng.util.BundlerUtil;
import org.wceng.util.FileUtil;

import java.io.File;
import java.util.List;

public class PoemsClient {

    public static void main(String[] args) {
        ProcessChain chain = new ProcessChain("https://www.xungushici.com/authors");
        chain.addProcess(PoetUrlProcess.class);
        chain.addProcess(PoetDetailProcess.class);
        chain.addProcess(PoemUrlProcess.class);
        chain.addProcess(PoemDetailProcess.class);
        Crawler.getInstance().addChain(chain).setup();


        chain.setDataListener(new DataListener() {
            @Override
            public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
                if (checker.is(PoetDetailProcess.class)) {
                    String s = BundlerUtil.convertMapToJson(bundlers);
                    FileUtil.saveStringToFile(s, new File("C:\\Users\\王程程\\Desktop\\test\\poets.txt"));
                }
                if (checker.is(PoemDetailProcess.class)) {
                    String s = BundlerUtil.convertMapToJson(bundlers);
                    FileUtil.saveStringToFile(s, new File("C:\\Users\\王程程\\Desktop\\test\\poems.txt"));
                }
            }

            @Override
            public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
                if (checker.is(PoemDetailProcess.class)) {
                    System.out.println(bundler.getHashMap().get("poet"));
                }
            }
        });
    }
}


