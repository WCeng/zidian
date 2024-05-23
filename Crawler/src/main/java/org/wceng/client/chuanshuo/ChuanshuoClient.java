package org.wceng.client.chuanshuo;

import com.google.gson.Gson;
import org.wceng.component.Crawler;
import org.wceng.component.Bundler;
import org.wceng.component.ProcessChain;
import org.wceng.util.BundlerUtil;

import java.util.List;

public class ChuanshuoClient {

    public static void main(String[] args) {
//        ProcessChain chain = new ProcessChain("http://www.bestgushi.com/m/m/index.html");
//        chain.addProcess(ChuanshuoUrlProcess.class);
//        chain.addProcess(ChuanshuoEntityProcess.class);
//        chain.setDataListener(new DataListener() {
//            @Override
//            public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
//                if (checker.is(ChuanshuoEntityProcess.class)) {
//                    String s = BundlerUtil.convertMapToJson(bundlers);
//                    System.out.println(s);
//                }
//                if (checker.is(ChuanshuoUrlProcess.class)) {
//                    String s = BundlerUtil.convertMapToJson(bundlers);
//                    System.out.println(s);
//                }
//            }
//
//            @Override
//            public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
//                if (checker.is(ChuanshuoEntityProcess.class)) {
//                    String s = BundlerUtil.convertMapToJson(bundler);
////                    System.out.println(s);
//                }
//                if (checker.is(ChuanshuoUrlProcess.class)) {
//                    String s = new Gson().toJson(bundler.getStringList());
//                    System.out.println(s);
//                }
//            }
//        });
//
//        Crawler.getInstance().addChain(chain).setup();
    }
}
