package com.pbl5.helpers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConvert {
    public static Timestamp convert(Long date) {
        try {
//            long time = Long.parseLong(date);
            Date currentDate = new Date(date * 1000);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            return new Timestamp(currentDate.getTime());
        } catch (Exception e) { //this generic but you can control another types of exception
            e.printStackTrace();
            return null;
        }
    }
    public static Long convertToLong(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.getTime();
    }

}
