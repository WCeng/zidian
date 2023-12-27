package org.wceng.component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Observer;

public class Data {

    private final HashMap<String, Object> map;

    public Data() {
        this.map = new LinkedHashMap<>();
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public HashMap<String, Object> getMap() {
        return map;
    }
}
