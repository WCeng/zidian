package org.wceng.client.antusheng;

import org.wceng.component.Crawler;
import org.wceng.component.Bundler;
import org.wceng.component.ProcessChain;
import org.wceng.util.BundlerUtil;
import org.wceng.util.FileUtil;

import java.io.File;
import java.util.List;

public class AntuShengClient {
    public static void main(String[] args) {
//        ProcessChain chain = new ProcessChain("https://www.qigushi.com/ats/");
//        chain.addProcess(AntushengUrlProcess.class);
//        chain.addProcess(AntushengEntityProcess.class);
//        chain.setDataListener(new DataListener(){
//            @Override
//            public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
//                if(checker.is(AntushengEntityProcess.class)){
//                    String s = BundlerUtil.convertMapToJson(bundler);
//                    System.out.println(s);
//                }
//            }
//
//            @Override
//            public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
//                if(checker.is(AntushengEntityProcess.class)){
//                    String s = BundlerUtil.convertMapToJson(bundlers);
//                    FileUtil.saveStringToFile(s, new File("C:\\Users\\王程程\\Desktop\\test\\antusheng.json"));
//                }
//            }
//        });
//
//        Crawler.getInstance().addChain(chain).setup();
    }
}
