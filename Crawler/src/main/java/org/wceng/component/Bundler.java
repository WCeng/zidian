package org.wceng.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Bundler {

    private final List<String> stringList;

    private final HashMap<String, Object> hashMap;

    private final List<String> nextLayerUrls;

    private final List<String> loopLayerUrl;

    public Bundler() {
        stringList = new ArrayList<>();
        hashMap = new LinkedHashMap<>();
        nextLayerUrls = new ArrayList<>();
        loopLayerUrl = new ArrayList<>();
    }

    public void putHashMap(String key, Object value) {
        hashMap.put(key, value);
    }

    public void putStringList(String str) {
        stringList.add(str);
    }

    public void putLoopLayerUrls(String url) {
        if (url != null)
            loopLayerUrl.add(url);
    }

    public void putNextLayerUrls(String url) {
        if (url != null)
            nextLayerUrls.add(url);
    }

    List<String> getNextLayerUrls() {
        return nextLayerUrls;
    }

    List<String> getLoopLayerUrl() {
        return loopLayerUrl;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

}
