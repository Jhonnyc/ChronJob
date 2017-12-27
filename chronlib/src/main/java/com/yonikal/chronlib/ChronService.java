package com.yonikal.chronlib;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yonikal.chronlib.fileLogger.FileLogger;
import com.yonikal.chronlib.fileLogger.LogFile;

import java.util.concurrent.TimeUnit;

/**
 * Created by yonikal on 11/25/15.
 */
public class ChronService extends Service {

    private static final String TAG = "ChronService";
    private static final int ALARM_MANAGER_REQUEST_CODE = 1223545632;
    private static long mServiceStartTime;

    //region Class Variables
    private static boolean mIsAlive = false;
    //endregion

    //region Binder (Note : Since this service is not binded to UI it returns null)
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //endregion

    //region Service Inherited Methods
    @Override
    public void onCreate() {
        super.onCreate();
        mServiceStartTime = System.currentTimeMillis();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FileLogger.addLog(LogFile.LOCATION, getBaseContext(), ChronService.class, "onStartCommand");
        mIsAlive = true;

        //Uncomment this to stop the alarm manager from starting
        // the service at morning and cancel the request once the service started
        stopAlarmTask();

        onWork();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mIsAlive = false;
        super.onDestroy();
    }
    //endregion

    //region Detect User Location
    public void onWork() {

        // This section is to keep the service sleeping in case the
        // last notification shown recently
        long timeToWakeUp = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30);
        boolean killSelf = (timeToWakeUp > System.currentTimeMillis());

        if (killSelf) {
            FileLogger.addLog(LogFile.LOCATION, getBaseContext(), ChronService.class,
                    "onWork");
            setWakeOnTime(timeToWakeUp);
        }
    }
    //endregion

    //region Private Helper Methods
    private void setWakeOnTime(long timeStamp) {
        Intent intent = new Intent(this, ChronService.class);
        PendingIntent action = PendingIntent.getService(this, ALARM_MANAGER_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, timeStamp, action);

        FileLogger.addLog(LogFile.LOCATION, getBaseContext(), ChronService.class, "setWakeOnTime Schedueled to wake on " + TimeUtils.getFormattedTime(timeStamp));
        stopSelf();
    }

    private void stopAlarmTask() {
        Intent intent = new Intent(this, ChronService.class);
        PendingIntent action = PendingIntent.getService(this, ALARM_MANAGER_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(action);

        FileLogger.addLog(LogFile.LOCATION, getBaseContext(), ChronService.class, "stopAlarmTask Canceling any schedueled task in case it still running.");
    }
    //endregion
}
