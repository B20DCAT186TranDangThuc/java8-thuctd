package com.digidinos.shopping.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreateForm {
    private String username;
    private String password;
    private String roleName;
    private boolean active;

}
