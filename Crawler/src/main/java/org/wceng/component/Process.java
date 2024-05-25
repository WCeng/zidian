package org.wceng.component;

import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Document;

public abstract class Process implements Runnable {

    private final Bundler bundler;

    private Connector connector;

    private boolean isCompleted;

    private Exception exception;

    @Nullable
    private String docTitle;

    public Process() {
        bundler = new Bundler();
    }

    @Override
    public void run() {
        try {
            Document document = connector.connect();

            if(document != null) {
                this.docTitle = document.title();
            }
            extractDocument(document);

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

    Connector getConnector() {
        return connector;
    }

    void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    Exception getException() {
        return exception;
    }

    String getDocTitle(){
        return docTitle;
    }


}
