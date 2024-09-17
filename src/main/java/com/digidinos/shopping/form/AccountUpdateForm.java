package com.digidinos.shopping.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountUpdateForm {
    private String username;
    private String roleName;
    private String password;
    private boolean active;

}
