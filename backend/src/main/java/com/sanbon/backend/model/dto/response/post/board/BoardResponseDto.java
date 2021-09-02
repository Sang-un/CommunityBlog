package com.sanbon.backend.model.dto.response.post.board;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private List<BoardListResponse> boardApiNoticeResponseList;
    private List<BoardListResponse> boardApiUrgentResponseList;
    private List<BoardListResponse> boardApiResponseList;
}
