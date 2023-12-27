package org.wceng;

import org.wceng.component.DataManager;
import org.wceng.component.ProcessChain;
import org.wceng.listener.DataListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static final File deskFile = new File("C:\\Users\\王程程\\Desktop\\test\\word.json");


    public static void main(String[] args) {
        ProcessChain processChain = new ProcessChain("https://www.chazidian.com/zidian/#zimulista");
        processChain.addProcess(PinyinUrlProcess.class);
        processChain.addProcess(WordUrlProcess.class);
        processChain.addProcess(WordEntityProcess.class);
        processChain.setDataListener(new DataListenerImpl());

        new Crawler().addChain(processChain).setup();
    }


    static class DataListenerImpl implements DataListener {

        @Override
        public void onReceivedData(DataManager dataManager) {
            if (dataManager.getProcessClass() == WordEntityProcess.class) {
                try {
                    strToFile(dataManager.dataToJson(), deskFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }


    private static void strToFile(String str, File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(str);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

}
