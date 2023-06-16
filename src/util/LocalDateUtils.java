package util;

import java.time.LocalDate;

public class LocalDateUtils {

    public static boolean isEqualAndBefore(LocalDate orgDate, LocalDate trgDate) {
        return (orgDate.isBefore(trgDate) || orgDate.isEqual(trgDate)) ? true : false;
    }

    public static boolean isEqualAndAfter(LocalDate orgDate, LocalDate trgDate) {
        return (orgDate.isAfter(trgDate) || orgDate.isEqual(trgDate)) ? true : false;
    }
}
