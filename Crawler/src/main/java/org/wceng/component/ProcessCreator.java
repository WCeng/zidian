package org.wceng.component;

import javax.net.ssl.SSLSocketFactory;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProcessCreator {

    private final ProcessLayer processLayer;
    private int connectTimeout;
    private Proxy proxy;
    private SSLSocketFactory sslSocketFactory;
    private int processConnectTime;


    public ProcessCreator(ProcessLayer processLayer) {
        this.processLayer = processLayer;
    }

    private Process createInstance()
            throws InstantiationException, IllegalAccessException {
        return processLayer.getProcessClass().newInstance();
    }

    public Process createProcess(String url) throws Exception {
        Process process = createInstance();
        Connector connector = new Connector(url, connectTimeout, proxy, sslSocketFactory, processConnectTime);
        process.setConnector(connector);
        return process;
    }


    public List<Process> createProcesses(List<String> urls) throws Exception {
        List<Process> processes = new ArrayList<>();
        for (String url : urls) {
            processes.add(createProcess(url));
        }
        return processes;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    public void setProcessConnectTime(int processConnectTime) {
        this.processConnectTime = processConnectTime;
    }
}
