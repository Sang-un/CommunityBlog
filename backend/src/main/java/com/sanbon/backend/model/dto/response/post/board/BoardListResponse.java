package com.sanbon.backend.model.dto.response.post.board;

import com.sanbon.backend.model.enumclass.SecondCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponse {
    private Long id;
    private SecondCategory category;
    private String title;
    private String writer;
    private LocalDateTime modifiedDate;

}
