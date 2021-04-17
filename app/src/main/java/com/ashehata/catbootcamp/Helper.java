package com.ashehata.catbootcamp;

import android.text.format.DateUtils;

public class Helper {

    public static String getSinceTime(long milli) {
        String agoTime = "";
        try {
            long passedTime = milli;
            long nowTime = System.currentTimeMillis();
            String convertedTime =
                    DateUtils.getRelativeTimeSpanString(passedTime, nowTime,
                            DateUtils.SECOND_IN_MILLIS).toString();
            agoTime = convertedTime;
        } catch (Exception e) {
            agoTime = "since unknown time";
        }
        return agoTime;
    }
}
