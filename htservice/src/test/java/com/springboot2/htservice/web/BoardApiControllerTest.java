package com.springboot2.htservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardRepository;
import com.springboot2.htservice.web.dto.BoardSaveRequestDto;
import com.springboot2.htservice.web.dto.BoardUpdateRequestDto;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                /*.apply(springSecurity())*/
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        boardRepository.deleteAll();
    }

    @Test
    //@WithMockUser
    public void registerBoard() throws Exception {
        //given
        String title = "title";
        String content = "content";
        BoardSaveRequestDto requestDto = BoardSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/board";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getViewCount()).isEqualTo(0L);
    }

    @Test
    public void updateBoard() throws Exception {
        //given
        Board saveBoard = boardRepository.save(Board.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long updateId = saveBoard.getId();
        String updateTitle = "title2";
        String updateContent = "content2";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/board/"+updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updateContent);

    }

    @Test
    public void deleteBoard() throws Exception{
        //given
        Board saveBoard = boardRepository.save(Board.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long deleteId = saveBoard.getId();

        String url = "http://localhost:"+port+"/api/v1/board/"+deleteId;

        //when
        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                /*.content(new ObjectMapper().writeValueAsString(requestDto))*/)
                .andExpect(status().isOk());

    }

}
