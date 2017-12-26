package com.yonikal.chronjob.fileLogger;

import android.location.Location;

import com.yonikal.chronjob.chron.TimeUtils;


/**
 * Created by yonikal on 12/14/15.
 */
class Log {

    String mLogLine = null;

    public Log(Class<?> clazz, String log) {
        mLogLine = String.format("%s | %s : %s", TimeUtils.getFormattedTime(), clazz.getSimpleName(), log);
    }

    public Log(Class<?> clazz, String log, Location location) {
        mLogLine = String.format("%s | %s : %s; (%s, %s)", TimeUtils.getFormattedTime(),
                clazz.getSimpleName(), log, String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()));
    }

    public <T> Log(Class<?> clazz, String logFormat, T... args) {
        String format = TimeUtils.getFormattedTime() + " | " + clazz.getSimpleName() + " : " + logFormat;
        mLogLine = String.format(format, args);
    }

    @Override
    public String toString() {
        return mLogLine;
    }
}
