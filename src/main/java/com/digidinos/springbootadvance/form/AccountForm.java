package com.digidinos.springbootadvance.form;

import com.digidinos.springbootadvance.validator.StrongPassword;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountForm {

    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username không được chứa ký tự đặc biệt")
    private String username;

    @StrongPassword
    private String password;
    private String role;
}
