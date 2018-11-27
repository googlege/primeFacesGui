package de.homedev.primefaces.api.model;

import java.nio.charset.Charset;

public interface AppConstants {
    public static final int MANDANTNAME_MAX_LENGTH = 256;
    public static final int USERDISPLAYNAME_MAX_LENGTH = 256;
    public static final int AUTHORITY_MAX_LENGTH = 100;
    public static final int IMAGE_MAX_LENGTH = 256;
    public static final int SHORT_TEXT_MAX_LENGTH = 256;
    public static final int LONG_TEXT_MAX_LENGTH = 256;

    public static final String AUTHENTICATION_EXCEPTION = "LOGIN_LAST_EXCEPTION";
    public static final String AUTHENTICATION_USERNAME = "AUTH_USER";
    public static final int USERNAME_MAX_LENGTH = 256;
    public static final int USERPASSWORD_MAX_LENGTH = 256;
    public static final int FIRSTNAME_MAX_LENGTH = 60;
    public static final int LASTNAME_MAX_LENGTH = 60;
    public static final int ADDRESS_MAX_LENGTH = 256;
    public static final int EMAIL_MAX_LENGTH = 100;
    public static final int PHONE_MAX_LENGTH = 50;
    public static final int BIC_MAX_LENGTH = 11;
    public static final int BLZ_MAX_LENGTH = 8;
    public static final int BANKNAME_MAX_LENGTH = 60;

    public static final int BLZ_ID = 0;
    public static final int BIC_ID = 1;
    public static final int BANKNAME_ID = 2;
    public static final int PLZ_ID = 3;
    public static final int ORT_ID = 4;

    public static final char CSV_PARAM_SEPARATOR = ';';
    public static final String CSV_PARAM_SEPARATOR_STR = ";";
    public static final char CSV_PARAM_SEPARATOR_REPLACEMENT = '|';
    public static final String RECORD_SEPARATOR = "\r\n";

    public static final Charset CHARSET = Charset.forName("ISO-8859-1");
    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    public static final int RECORDS_PER_FILE = 400;
    public static final String INDEX_FILENAME = "blz.idx";
}
