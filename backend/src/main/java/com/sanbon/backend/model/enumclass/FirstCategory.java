package com.sanbon.backend.model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum FirstCategory {
    DATA(0, "자료게시판", "자료게시판"),
    BOARD(1, "게시판", "게시판");

    private final Integer id;
    private final String title;
    private final String content;

}
