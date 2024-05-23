package org.wceng.component;

import org.jsoup.nodes.Document;

public abstract class Process implements Runnable {

    private final Bundler bundler;

    private Connector connector;

    private boolean isCompleted;

    private Exception exception;

    public Process() {
        bundler = new Bundler();
    }

    @Override
    public void run() {
        try {
            extractDocument(connector.connect());
        } catch (Exception e) {
            exception = e;
        }
    }

    public abstract void extractDocument(Document doc) throws Exception;

    void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Bundler getBundler() {
        return bundler;
    }

    public String getUrl() {
        return connector.getUrl();
    }

    boolean isCompleted() {
        return isCompleted;
    }

    void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Exception getException() {
        return exception;
    }
}
