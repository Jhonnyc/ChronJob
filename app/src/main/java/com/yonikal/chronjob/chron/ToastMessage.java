package com.yonikal.chronjob.chron;

import android.widget.Toast;
import android.content.Context;

/**
 * Created by yonikal on 11/25/15.
 */
public class ToastMessage implements Runnable {
    private final Context mContext;
    private String mText;

    public ToastMessage(final Context context, String text) {
        mContext = context;
        mText = text;
    }

    @Override
    public void run() {
        Toast.makeText(mContext, mText, Toast.LENGTH_SHORT).show();
    }
}