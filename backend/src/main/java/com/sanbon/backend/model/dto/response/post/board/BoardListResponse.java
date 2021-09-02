package com.sanbon.backend.model.dto.response.post.board;

import com.sanbon.backend.model.enumclass.BulletinStatus;
import com.sanbon.backend.model.enumclass.SecondCategory;
import com.sanbon.backend.model.post.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BoardListResponse {
    private Long id;
    private SecondCategory category;
    private String title;
    private BulletinStatus status;
    private String writer;
    private LocalDateTime createdDate;

    private Long views;
    private Boolean hasFile;

    public static BoardListResponse toEntity(Board board) {
        return BoardListResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .category(board.getCategory())
                .status(board.getStatus())
                .writer(board.getWriter())
                .views(board.getViews())
                .createdDate(board.getCreatedDate())
                .build();
    }

}
