package com.digidinos.shopping.validator;

import com.digidinos.shopping.form.AccountCreateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountCreateFormValidator implements Validator {

    // Validator này chỉ dùng để kiểm tra đối với AccountCreateForm.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountCreateForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountCreateForm accountForm = (AccountCreateForm) target;

        // Kiểm tra các trường (field) của AccountCreateForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "","Username can not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName","", "Role can not be empty");

        // Kiểm tra mật khẩu
        if (!isValidPassword(accountForm.getPassword())) {

            errors.rejectValue("password", "", "Password must be at least 8 characters long and contain at least one uppercase letter and one special character");
        }

    }

    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$";
        return password.matches(regex);
    }

}