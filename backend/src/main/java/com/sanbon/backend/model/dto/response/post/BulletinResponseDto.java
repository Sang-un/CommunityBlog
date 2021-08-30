package com.sanbon.backend.model.dto.response.post;

import lombok.Getter;

import java.util.List;

@Getter
public class BulletinResponseDto {

    private List<BulletinListResponse> bulletinApiNoticeResponseList;
    private List<BulletinListResponse> bulletinApiUrgentResponseList;
    private List<BulletinListResponse> bulletinApiResponseList;
}
