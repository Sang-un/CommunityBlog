package com.sanbon.backend.model.dto.response.post;

import com.sanbon.backend.model.enumclass.BulletinStatus;
import com.sanbon.backend.model.enumclass.FirstCategory;
import com.sanbon.backend.model.enumclass.SecondCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BulletinListResponse {
    private Long id;
    private FirstCategory firstCategory;
    private SecondCategory secondCategory;
    private String title;
    private BulletinStatus status;
    private String writer;
    private LocalDateTime createdAt;
    private Long views;

}
