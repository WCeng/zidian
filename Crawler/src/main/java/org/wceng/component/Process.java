package org.wceng.component;

import org.jsoup.nodes.Document;

public abstract class Process implements Runnable {

    DataManager dataManager;

    Connector connector;

    @Override
    public void run() {
        try {
            extractDocument(connector.connect());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public abstract void extractDocument(Document document);

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
