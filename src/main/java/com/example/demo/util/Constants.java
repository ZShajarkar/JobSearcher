package com.example.demo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    //regx
    public static final String STRONG_PASSWORD_PATTERN = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,20}$";
    public static final String VALID_EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
    public static final String JUST_PERSIAN_PATTERN = "^[\\u0622\\u0627\\u0628\\u067E\\u062A-\\u062C\\u0686\\u062D-\\u0632\\u0698\\u0633-\\u063A\\u0641\\u0642\\u06A9\\u06AF\\u0644-\\u0648\\u06CC]+$";
    //cron
    public static final String EVERY_DAY = "0 1 1 * * ?";
}
