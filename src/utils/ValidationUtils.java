//utils/ValidationUtils.java

package utils;

import java.util.Date;

public class ValidationUtils {
    // Drug code validation (example: D-1234 format)
    public static boolean isValidDrugCode(String code) {
        return code != null && code.matches("D-\\d{4}");
    }

    // Price validation
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    // Expiration date validation
    public static boolean isValidExpirationDate(Date date) {
        return date != null && date.after(new Date());
    }
}
