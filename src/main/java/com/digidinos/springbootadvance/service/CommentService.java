package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Comment;
import com.digidinos.springbootadvance.form.CommentForm;
import com.digidinos.springbootadvance.repository.CommentRepository;
import com.digidinos.springbootadvance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    public Comment saveComment(CommentForm form) {

        Comment comment = Comment.builder()
                .authorName(form.getAuthorName())
                .authorEmail(form.getAuthorEmail())
                .content(form.getContent())
                .product(productRepository.findById(form.getProductId()).get())
                .build();

        comment.setCreateAt(LocalDateTime.now());
        comment.setUpdateAt(LocalDateTime.now());

        return commentRepository.save(comment);

    }

    public List<Comment> findAllByProductId(Long productId) {
        return commentRepository.findByProductId(productId);
    }
}
