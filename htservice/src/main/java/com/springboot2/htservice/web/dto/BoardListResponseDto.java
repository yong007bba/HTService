package com.springboot2.htservice.web.dto;

import com.springboot2.htservice.domain.board.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String author;
    private Long viewCount;
    private LocalDateTime createdDate;

    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.viewCount = entity.getViewCount();
        this.createdDate = entity.getCreatedDate();
    }
}
