package com.yonikal.chronlib.fileLogger;

import android.content.Context;
import android.location.Location;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by yonikal on 12/14/15.
 */
public class FileLogger {

    private static final String TAG = "FileLogger";
    private static final String LOG_DIR_NAME = "Log";

    public static void addLog(LogFile fileName, Context context, Class<?> clazz, String logLine) {
            Log log = new Log(clazz, logLine);
            addLog(fileName, context, log);

    }

    public static void addLog(LogFile fileName, Context context, Class<?> clazz, String logLine, Location location) {
            Log log = new Log(clazz, logLine, location);
            addLog(fileName, context, log);

    }

    public static <T> void addRestrictedLog(LogFile fileName, Context context, Class<?> clazz, String logFormat, T... args) {
            Log log = new Log(clazz, logFormat, args);
            addLog(fileName, context, log);

    }

    public static <T> void addLog(LogFile fileName, Context context, Class<?> clazz, String logFormat, T... args) {
            Log log;
            String[] argsStr;
            try {
                argsStr = new String[args.length];
                int i = 0;
                for (T arg : args) {
                    argsStr[i] = String.valueOf(arg);
                    i++;
                }
                log = new Log(clazz, logFormat, argsStr);
                addLog(fileName, context, log);
            } catch (Exception e) {
                //Log.e(TAG, e.getMessage());
            }

    }

    /**
     * Helper method to save the data to physical file on the device
     *
     * @param context The app context (used to save the data)
     * @param logLine The log line
     */
    private synchronized static void addLog(final LogFile fileName, final Context context, final Log logLine) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                        File file;
                        if (isExternalWritable()) {
                            file = getWritableFile(context, fileName, true);
                        } else {
                            file = getWritableFile(context, fileName, false);
                        }
                        if (file != null) {
                            FileWriter writer = new FileWriter(file, true);
                            writer.append(String.format("%n%s", logLine.toString()));
                            writer.close();
                        } else {
                            //Log.e(TAG, "Couldn't open file for writing (it came back as null).");
                        }
                    } catch (Exception e) {
                        //Log.e(TAG, "Exception when trying to write to file. details - " + e.getMessage() + " cause - " + e.getCause());
                    }
                }
            }).start();
        } catch (Exception e) {
            //Log.e(TAG, "Exception when trying to write to file. details - " + e.getMessage() + " cause - " + e.getCause());
        }
    }

    private static boolean isExternalWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);

    }

    private static File getWritableFile(Context context, LogFile fileName, boolean onSdCard) {
        File file = null;
        try {
            if (!onSdCard) {
                file = new File(context.getFilesDir(), fileName.getFileName());
            } else {
                String logDirectory = Environment.getExternalStorageDirectory() + File.separator + LOG_DIR_NAME;
                File dir = new File(logDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file = new File(dir, fileName.getFileName());
            }

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (Exception e) {
//            Log.e(TAG, "Failed to create file with excpetion message "
//                    + e.getMessage() + " and cause "
//                    + e.getCause());
        }

        return file;
    }
}