package org.wceng.client.yilin;

import org.wceng.component.Crawler;
import org.wceng.component.Bundler;
import org.wceng.component.Config;
import org.wceng.component.ProcessChain;
import org.wceng.listener.DataListener;
import org.wceng.util.BundlerUtil;
import org.wceng.util.FileUtil;

import java.io.File;
import java.util.List;

public class YilinClient {

    public static void main(String[] args) throws Exception {

        ProcessChain chain = new ProcessChain("https://www.yilinzazhi.com/");

        Config config = chain.getConfig();
        config.setIgnoreCertValida(true);
        config.setMaxThreadCount(30);

        chain.addProcess(YilinBookUrlProcess.class);
        chain.addProcess(YilinSectionUrlProcess.class);
        chain.addProcess(YilinSectionEntityProcess.class);
        chain.setDataListener(new DataListener() {
            @Override
            public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
                if (checker.is(YilinSectionEntityProcess.class)) {
                    System.out.println(BundlerUtil.convertMapToJson(bundler));
                }
            }

            @Override
            public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
                if (checker.is(YilinSectionEntityProcess.class)) {
                    String s = BundlerUtil.convertMapToJson(bundlers);
                    FileUtil.saveStringToFile(s, new File("C:\\Users\\王程程\\Desktop\\test\\yilin.json"));
                }
            }
        });

        Crawler.getInstance().addChain(chain).setup();
    }


}
