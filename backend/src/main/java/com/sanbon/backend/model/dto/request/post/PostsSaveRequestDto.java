package com.sanbon.backend.model.dto.request.post;

import lombok.Getter;

@Getter
public class PostsSaveRequestDto {
    private String title;
    private String author;
    private String email;
    private String content;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .author(author)
                .email(email)
                .content(content)
                .build();
    }
}
