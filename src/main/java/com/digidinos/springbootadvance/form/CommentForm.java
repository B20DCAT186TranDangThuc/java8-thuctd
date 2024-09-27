package com.digidinos.springbootadvance.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentForm {

    private Long productId;

    @NotBlank(message = "Comment không được để trống")
    @Size(min = 5, max = 300, message = "Comment phải có độ dài từ 5 đến 300 ký tự")
    private String content;

    @NotBlank(message = "Name name is required.")
    private String authorName;

    @Email(message = "Email should be valid and in the correct format.")
    private String authorEmail;

}
