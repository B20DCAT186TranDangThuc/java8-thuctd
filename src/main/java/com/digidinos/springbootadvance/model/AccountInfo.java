package com.digidinos.springbootadvance.model;

import com.digidinos.springbootadvance.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String role;


}
