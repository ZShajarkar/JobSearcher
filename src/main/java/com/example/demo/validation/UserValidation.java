package com.example.demo.validation;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {
    private final UserRepository userRepository;

    @Autowired
    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUser(UserDto userInfo) throws Exception {
        Validation.notEmpty(userInfo.getFirstName(), ExceptionMessage.FIRST_NAME_MUST_BE_FILLED);
        Validation.notEmpty(userInfo.getLastName(), ExceptionMessage.LAST_NAME_MUST_BE_FILLED);
        Validation.validateEmail(userInfo.getEmail());
        Validation.notEqual(userInfo.getConfirmedPassword(), userInfo.getPassword(), ExceptionMessage.PASSWORD_DOESNT_MATCH);
        Validation.checkComplicatePassword(userInfo.getPassword());
        checkUniqueEmail(userInfo.getEmail());
    }

    private void checkUniqueEmail(String email) throws Exception {
        if (userRepository.findByEmail(email) != null)
            throw new Exception(ExceptionMessage.EMAIL_HAS_BEEN_REGISTERED);
    }


}
