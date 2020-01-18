package com.task.telna.validator;

import com.task.telna.constants.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return phoneNumber.matches(Constants.PHONE_NUMBER_REGEX);
    }
}
