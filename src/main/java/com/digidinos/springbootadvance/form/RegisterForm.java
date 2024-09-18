package com.digidinos.springbootadvance.form;

import com.digidinos.springbootadvance.validator.RegisterChecked;
import com.digidinos.springbootadvance.validator.StrongPassword;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RegisterChecked
public class RegisterForm {
    @Size(min = 4, message = "username must have at least 4 characters")
    private String username;

    @StrongPassword
    private String password;

    private String confirmPassword;

}
