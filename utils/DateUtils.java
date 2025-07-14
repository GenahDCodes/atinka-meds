package utils;

import java.util.Date;

public class DateUtils {
    public static boolean isExpired(Date expirationDate) {
        if (expirationDate == null) return true;
        return expirationDate.before(new Date());
    }
    
    public static boolean isExpiringSoon(Date expirationDate, int daysThreshold) {
        if (expirationDate == null) return false;
        Date thresholdDate = new Date(System.currentTimeMillis() + daysThreshold * 24 * 60 * 60 * 1000L);
        return expirationDate.before(thresholdDate);
    }
}