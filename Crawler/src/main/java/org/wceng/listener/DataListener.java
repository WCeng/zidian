package org.wceng.listener;

import org.wceng.component.Data;
import org.wceng.component.DataManager;

public interface DataListener {

    void onReceivedData(DataManager dataManager);
}
