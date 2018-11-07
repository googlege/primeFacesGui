package de.homedev.primefaces.api.util;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class SqlUtils {

    private static final String APOSTROPHE = "'";
    private static final String SQL_APOSTROPHE_REPLACEMENT = "''";

    private SqlUtils() {
    }

    public static String checkValue(String value) {
        if (value == null) {
            return value;
        }
        return value.replace(APOSTROPHE, SQL_APOSTROPHE_REPLACEMENT);
    }

}
