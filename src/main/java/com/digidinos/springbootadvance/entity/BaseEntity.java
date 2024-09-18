package com.digidinos.springbootadvance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected Long id;

    @Column(nullable = false)
    protected LocalDateTime createAt;
    @Column(nullable = false)
    protected LocalDateTime updateAt;
    protected LocalDateTime deleteAt;
    private boolean isDeleted = Boolean.FALSE;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
