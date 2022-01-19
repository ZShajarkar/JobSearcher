package com.example.demo.validation;

import com.example.demo.exception.ExceptionMessage;
import com.example.demo.util.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.xml.bind.ValidationException;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Validation {

    public static void notEqual(String firstInput, String secondInput, String message) {
        if (!firstInput.equals(secondInput))
            throw new InputMismatchException(message);
    }

    public static void notEmpty(String inout, String message) throws ValidationException {
        if (inout.trim().isEmpty())
            throw new ValidationException(message);
    }

    public static void checkComplicatePassword(String text) throws ValidationException {
        if (!text.matches(Constants.STRONG_PASSWORD_PATTERN))
            throw new ValidationException(ExceptionMessage.PASSWORD_IS_NOT_STRONG);
    }

    public static void validateEmail(String email) throws ValidationException {
        if (!Pattern.compile(Constants.VALID_EMAIL_PATTERN)
                .matcher(email)
                .matches())
            throw new ValidationException(ExceptionMessage.EMAIL_IS_NOT_VALID);
    }

    public static void notNull(Object input, String message) throws Exception {
        if (input == null)
            throw new Exception(message);
    }

    public static void validateIfPersian(String input, String message) throws ValidationException {
        if (!Pattern.compile(Constants.JUST_PERSIAN_PATTERN)
                .matcher(input.trim())
                .matches())
            throw new ValidationException(message);
    }

    public static void checkValidLength(int min, int max, String message, String input) throws ValidationException {
        if (input.trim().length() < min || input.length() > max)
            throw new ValidationException(message);
    }


}
