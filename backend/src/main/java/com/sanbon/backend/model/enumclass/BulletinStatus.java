package com.sanbon.backend.model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BulletinStatus {

    URGENT(0, "긴급", "긴급 게시글"),
    NOTICE(1, "자료", "자료 게시글"),
    REVIEW(2, "후기", "여행 게시글");

    private final Integer id;
    private final String title;
    private final String description;

}
