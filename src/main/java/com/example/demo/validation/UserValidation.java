package com.example.demo.validation;

import com.example.demo.dto.JobsForEachCompanyDto;
import com.example.demo.dto.SignUpUserRequestDto;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class UserValidation {
    private final UserRepository userRepository;
    private final JobService jobService;

    @Autowired
    public UserValidation(UserRepository userRepository, JobService jobService) {
        this.userRepository = userRepository;
        this.jobService = jobService;
    }

    public void validateUser(SignUpUserRequestDto userInfo) throws ValidationException {
        Validation.notEmpty(userInfo.getFirstName(), ExceptionMessage.FIRST_NAME_MUST_BE_FILLED);
        Validation.notEmpty(userInfo.getLastName(), ExceptionMessage.LAST_NAME_MUST_BE_FILLED);
        Validation.notEmpty(userInfo.getEmail(), ExceptionMessage.EMAIL_MUST_BE_FILLED);
        Validation.notEmpty(userInfo.getPassword(), ExceptionMessage.PASSWORD_MUST_BE_FILLED);

        Validation.checkValidLength(2, 20,
                ExceptionMessage.FIRST_NAME_MUST_BE_BETWEEN_2_AND_20, userInfo.getFirstName());
        Validation.checkValidLength(2, 20,
                ExceptionMessage.LAST_NAME_MUST_BE_BETWEEN_2_AND_20, userInfo.getLastName());

        Validation.validateEmail(userInfo.getEmail());

        Validation.notEqual(userInfo.getConfirmedPassword(), userInfo.getPassword(), ExceptionMessage.PASSWORD_DOESNT_MATCH);

        Validation.checkComplicatePassword(userInfo.getPassword());

        checkUniqueEmail(userInfo.getEmail());

        Validation.validateIfPersian(userInfo.getFirstName(), ExceptionMessage.FIRST_NAME_MUST_BE_LETTER);
        Validation.validateIfPersian(userInfo.getLastName(), ExceptionMessage.LAST_NAME_MUST_BE_LETTER);
    }

    public void validateCompanyAccess(String token, Long jobID) throws ValidationException {
        Stream<Long> longStream = jobService.findJobsByCompanyId(token).stream().map(JobsForEachCompanyDto::getId);
        if (longStream.noneMatch(item -> Objects.equals(item, jobID)))
            throw new ValidationException(ExceptionMessage.YOU_DONT_HAVE_ACCESS_TO_THIS_JOB);
    }

    private void checkUniqueEmail(String email) throws ValidationException {
        if (userRepository.findByEmail(email) != null)
            throw new ValidationException(ExceptionMessage.EMAIL_HAS_BEEN_REGISTERED);
    }


}
