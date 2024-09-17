package com.digidinos.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    @Id
    @Column(name = "User_Name", length = 20, nullable = false)
    private String userName;
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;
    @Column(name = "Active", length = 1, nullable = false)
    private boolean active;
    @Column(name = "User_Role", length = 20, nullable = false)
    private String userRole;

    @Column(name = "Is_Deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "Create_At")
    private LocalDateTime createAt;

    @Column(name = "Update_At")
    private LocalDateTime updateAt;

    @Column(name = "Delete_At")
    private LocalDateTime deleteAt;
}