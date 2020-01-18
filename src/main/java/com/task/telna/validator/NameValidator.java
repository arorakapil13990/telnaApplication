package com.task.telna.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<UserName, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return (name != null) && (!name.equals("")) && (name.chars().allMatch(Character::isLetter));
    }
}
