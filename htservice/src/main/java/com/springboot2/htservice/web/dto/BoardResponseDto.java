package com.springboot2.htservice.web.dto;

import com.springboot2.htservice.domain.post.Board;

public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
