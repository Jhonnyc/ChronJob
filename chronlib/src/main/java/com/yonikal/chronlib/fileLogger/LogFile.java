package com.yonikal.chronlib.fileLogger;

/**
 * Created by yonikal on 12/15/15.
 */
public enum LogFile {

    LOCATION("log.txt");

    private String mFileName;

    LogFile(String fileName) {
        mFileName = fileName;
    }

    public String getFileName() {
        return mFileName;
    }
}
