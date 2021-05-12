package com.springboot2.htservice.domain.board;

import com.springboot2.htservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Long id;

    @Column(length = 500, nullable = false, name = "P_TITLE")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false, name = "P_CONTENT")
    private String content;

    @Column(name = "P_AUTHOR")
    private String author;

    @Column(name = "P_VIEWCOUNT")
    private Long viewCount = 0L;

//    @ManyToOne
//    @JoinColumn(name = "U_ID")
//    private User user;

    @Builder
    public Board(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
