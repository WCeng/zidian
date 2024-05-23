package org.wceng.client.gushi;

import org.wceng.component.Bundler;
import org.wceng.component.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.util.BundlerUtil;
import org.wceng.util.FileUtil;

import java.io.File;
import java.util.List;

public class GushiClient {
    public static void main(String[] args) throws Exception {

//        ProcessChain chain = new ProcessChain("https://www.gushiji.cc/shiren/1.html");
//        chain.getConfig().setIgnoreCertValida(true);
////        chain.getConfig().setProxy("117.57.92.202", 8089);
//
//
//        chain.addProcess(PoetUrlProcess.class);
//        chain.addProcess(PoetryUrlProcess.class);
//        chain.addProcess(PoetEntityProcess.class);
//        chain.setDataListener(new GushiDataListener());
//
//        Crawler.getInstance().addChain(chain).setup();
//    }
//
//    static class GushiDataListener extends DataListener {
//        @Override
//        public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
//            if (checker.is(2)) {
//                String s = BundlerUtil.convertMapToJson(bundlers);
//                FileUtil.saveStringToFile(s, new File("C:\\Users\\王程程\\Desktop\\test\\poetry.json"));
//            }
//        }
//
//        @Override
//        public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
//            System.out.println(BundlerUtil.convertMapToJson(bundler));
//        }
    }
}
