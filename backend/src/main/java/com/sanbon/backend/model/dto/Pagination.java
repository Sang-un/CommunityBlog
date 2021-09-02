package com.sanbon.backend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pagination {
    private Integer totalPage;
    private Long totalElements;
    private Integer currentPage;
    private Integer currentElements;
}
