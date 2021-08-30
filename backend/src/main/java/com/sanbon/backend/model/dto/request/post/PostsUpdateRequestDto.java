package com.sanbon.backend.model.dto.request.post;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;

}
