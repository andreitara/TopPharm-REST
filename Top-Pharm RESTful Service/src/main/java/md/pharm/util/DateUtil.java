package md.pharm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Andrei on 10/25/2015.
 */
public class DateUtil {
    private static int[] dayArray = new int[]{6,7,1,2,3,4,5,6,7};

    public static boolean isAfterCurrentWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = dayArray[calendar.get(Calendar.DAY_OF_WEEK)];
        calendar.add(Calendar.DAY_OF_YEAR, 7 - dayOfWeek);
        return date.after(calendar.getTime());
    }

    public static boolean isBeforeCurrentWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = dayArray[calendar.get(Calendar.DAY_OF_WEEK)];
        calendar.add(Calendar.DAY_OF_YEAR, 1 - dayOfWeek);
        return date.before(calendar.getTime());
}

    public static boolean isInCurrentWeek(Date date) {
        return (!isAfterCurrentWeek(date) && !isBeforeCurrentWeek(date));
    }

    public static String getFirstDayForCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        String result = calendar.get(Calendar.YEAR) + "-01-01";
        return result;
    }

    public static String getFirstDayForCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat( "MM" );
        String month = formatter.format(new java.util.Date());
        String result = calendar.get(Calendar.YEAR) + "-" + month + "-01";
        return result;
    }

    public static String getLastDayForCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        String result = calendar.get(Calendar.YEAR) + "-12-31";
        return result;
    }

    public static String getLastDayForCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat( "MM" );
        String month = formatter.format(new java.util.Date());
        String result = calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return result;
    }

}
