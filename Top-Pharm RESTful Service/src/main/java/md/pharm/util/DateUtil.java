package md.pharm.util;

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

}
