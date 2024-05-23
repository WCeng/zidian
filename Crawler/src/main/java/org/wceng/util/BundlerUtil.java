package org.wceng.util;

import com.google.gson.Gson;
import org.wceng.component.Bundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BundlerUtil {

    public static String convertListToJson(Bundler bundler) {
        Gson gson = new Gson();
        return gson.toJson(bundler.getStringList());
    }

    public static String convertListToJson(List<Bundler> bundlers) {
        Gson gson = new Gson();
        List<String> stringList = new ArrayList<>();
        for (Bundler b : bundlers) {
            if (!b.getStringList().isEmpty()) {
                stringList.addAll(b.getStringList());
            }
        }
        return gson.toJson(stringList);
    }

    public static String convertMapToJson(List<Bundler> bundlers) {
        Gson gson = new Gson();
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Bundler bundler : bundlers) {
            if (isMapDataValid(bundler)) {
                dataList.add(bundler.getHashMap());
            }
        }
        return gson.toJson(dataList);
    }

    public static String convertMapToJson(Bundler bundler) {
        Gson gson = new Gson();
        return gson.toJson(bundler.getHashMap());
    }

    private static boolean isMapDataValid(Bundler bundler) {
        boolean result = false;

        if (!bundler.getHashMap().isEmpty()) {
            for (Object value : bundler.getHashMap().values()) {
                if (!value.toString().isEmpty()) {
                    result = true;
                }
            }
        }

        return result;
    }
}
