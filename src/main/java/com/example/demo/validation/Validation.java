package com.example.demo.validation;

import com.example.demo.exception.ExceptionMessage;
import com.example.demo.util.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Validation {

    public static void notEqual(String firstInput, String secondInput, String message) {
        if (!firstInput.equals(secondInput))
            throw new InputMismatchException(message);
    }

    public static void notEmpty(String inout, String message) throws Exception {
        if (inout.trim().isEmpty())
            throw new Exception(message);
    }

    public static void checkComplicatePassword(String text) throws Exception {
        if (!text.matches(Constants.STRONG_PASSWORD_PATTERN))
            throw new Exception(ExceptionMessage.PASSWORD_IS_NOT_STRONG);
    }

    public static void validateEmail(String email) throws Exception {
        if (!Pattern.compile(Constants.VALID_EMAIL_PATTERN)
                .matcher(email)
                .matches())
            throw new Exception(ExceptionMessage.EMAIL_IS_NOT_VALID);
    }

}