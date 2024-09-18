package com.digidinos.springbootadvance.form;

import com.digidinos.springbootadvance.validator.StrongPassword;
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

    private String username;

    @StrongPassword
    private String password;
    private String role;
}
