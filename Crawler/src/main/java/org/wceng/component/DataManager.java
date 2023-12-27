package org.wceng.component;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class DataManager {

    private List<Data> dataList;
    private List<String> nextLayerUrls;
    private Class<? extends Process> processClass;

    public DataManager(Class<? extends Process> processClass) {
        this.processClass = processClass;
        this.dataList = new ArrayList<>();
        this.nextLayerUrls = new ArrayList<>();
    }

    public synchronized void putNextLayerUrls(String url) {
        nextLayerUrls.add(url);
    }

    public synchronized void putData(Data data) {
        dataList.add(data);
    }

    public List<String> getNextLayerUrls() {
        return nextLayerUrls;
    }

    public List<Data> getAllData() {
        return dataList;
    }

    public Class<? extends Process> getProcessClass() {
        return processClass;
    }

    public String dataToJson() {
        List<HashMap<String, Object>> mapList = new ArrayList<>();
        for (Data data : dataList) {
            mapList.add(data.getMap());
        }
        return new Gson().toJson(mapList);
    }

}