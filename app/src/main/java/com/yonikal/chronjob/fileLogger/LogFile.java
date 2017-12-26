package com.yonikal.chronjob.fileLogger;

/**
 * Created by yonikal on 12/15/15.
 */
public enum LogFile {

    LOCATION("locationLogFile.txt"),
    BLUETOOTH_STATE_LOG("bluetoothState.txt");

    private String mFileName;

    LogFile(String fileName) {
        mFileName = fileName;
    }

    public String getFileName() {
        return mFileName;
    }
}
