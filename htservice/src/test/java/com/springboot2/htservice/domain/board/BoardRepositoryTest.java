package com.springboot2.htservice.domain.board;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    public void cleanUp(){
        boardRepository.deleteAll();
    }

    @Test
    public void insertNreadArticle(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .author("Test@gmail.com")
                .build());

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getAuthor()).isEqualTo("Test@gmail.com");
        assertThat(board.getViewCount()).isEqualTo(0L);
    }


    @Test
    public void registBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        boardRepository.save(Board.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);
        System.out.println(">>>>>>>>> createDate=" + board.getCreatedDate() + ", modifiedDate=" + board.getModifiedDate());

        assertThat(board.getCreatedDate()).isAfter(now);
        assertThat(board.getModifiedDate()).isAfter(now);
    }
}
