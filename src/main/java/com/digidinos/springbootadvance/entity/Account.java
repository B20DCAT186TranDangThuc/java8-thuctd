package com.digidinos.springbootadvance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    private String role;

    @OneToOne(mappedBy = "account")
    private Cart cart;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Account{" +
                "deleteAt=" + deleteAt +
                ", updateAt=" + updateAt +
                ", createAt=" + createAt +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
