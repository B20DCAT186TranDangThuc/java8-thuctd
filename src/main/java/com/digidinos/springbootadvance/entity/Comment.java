package com.digidinos.springbootadvance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(length = 1000)
    private String content;

    private String authorName;

    private String authorEmail;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
