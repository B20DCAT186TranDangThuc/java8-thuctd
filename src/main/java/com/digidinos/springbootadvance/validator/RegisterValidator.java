package com.digidinos.springbootadvance.validator;

import com.digidinos.springbootadvance.form.RegisterForm;
import com.digidinos.springbootadvance.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterForm> {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean isValid(RegisterForm account, ConstraintValidatorContext context) {
        boolean valid = true;

        if (!account.getPassword().equals(account.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        // Additional validations can be added here
        // check username
        if (accountService.getAccountByUsername(account.getUsername()) != null) {
            context.buildConstraintViolationWithTemplate("Username is already in use")
                    .addPropertyNode("username")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
