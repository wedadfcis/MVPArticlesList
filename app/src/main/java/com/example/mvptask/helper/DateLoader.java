package com.example.mvptask.helper;

import android.widget.TextView;

public class DateLoader {

    public static void loadDate(TextView textView, String timeStr) {
        textView.setText(timeStr.substring(0, timeStr.indexOf("T")));

    }
}
