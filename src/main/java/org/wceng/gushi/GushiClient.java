package org.wceng.gushi;

import org.wceng.Crawler;
import org.wceng.component.ProcessChain;
import org.wceng.listener.DataListener;

public class GushiClient {

    public static void main(String[] args) {
        ProcessChain chain = new ProcessChain("https://www.gushici.net/zuozhe/");
        chain.addProcess(PoetUrlProcess.class);
        chain.setDataListener(new DataListener() {
            @Override
            public void onReceivedData(DataManager dataManager) {
                System.out.println(dataManager.dataToJson());
            }
        });

        new Crawler().addChain(chain).setup();
    }
}
