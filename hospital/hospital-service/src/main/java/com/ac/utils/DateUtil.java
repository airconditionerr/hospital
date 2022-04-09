package com.ac.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TimeUtil
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 17:06
 * @Version 1.0
 **/
public class DateUtil {
    public static String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(currentDate);
    }
}
