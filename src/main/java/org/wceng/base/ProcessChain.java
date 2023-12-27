package org.wceng.base;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProcessChain {

    private List<BaseProcess> baseProcesses;

    public ProcessChain() {
        this.baseProcesses = new LinkedList<>();
    }

    public void addProcess(BaseProcess process){

    }

    public void removeProcess(BaseProcess process){

    }

    public List<BaseProcess> get(){
        return baseProcesses;
    }


}
