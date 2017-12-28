package me.zgy.utils;

import io.searchbox.strings.StringUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rayee on 2017/12/28.
 */
public class DateUtils {

    public static final String yyyyMMdd="yyyy-MM-dd";
    private static final String default_date_time_pattern = "yyyy-MM-dd HH:mm:ss";

    public static Date set(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public static Date afterHours(Date now, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public static Date afterDays(Date now, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(set(now, 23, 59, 59));
        calendar.add(Calendar.DAY_OF_YEAR, day);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public static Date afterSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static Date formatDateTime(String str) throws ParseException {
        return new SimpleDateFormat(default_date_time_pattern).parse(str);
    }

    public static Date parseDate(String str,String model) throws ParseException {
        return new SimpleDateFormat(model).parse(str);
    }

    public static String formatDate(Date date, String model) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(model)) {
            model = default_date_time_pattern;
        }
        return new SimpleDateFormat(model).format(date);

    }
    /* 今天0点0分0秒00 */
    public static Date startOfToday(){
        return DateTime.now().withTimeAtStartOfDay().toDate();
    }

    /* 今天23点59分59秒999 */
    public static Date endOfToday(){
        return DateTime.now().millisOfDay().withMaximumValue().toDate();
    }

}
