package org.wceng.util;

import java.io.*;

public class FileUtil {

    public static boolean saveStringToFile(String content, File f) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean appendStringToFile(String content, File f) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, true))) {
            writer.write(content);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readStringFromFile(File f) {
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 如果发生异常，则返回null
    }

}
