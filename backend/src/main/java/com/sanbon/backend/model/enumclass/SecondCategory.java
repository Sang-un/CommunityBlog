package com.sanbon.backend.model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SecondCategory {
    FOOD(0, "음식", "맛집"),
    REVIEW(1, "후기", "의견"),
    VIDEO(2, "영상", "영상"),
    IMAGE(3, "이미지", "이미지"),
    FILE(4, "파일", "파일");

    private final Integer id;
    private final String title;
    private final String content;


}
