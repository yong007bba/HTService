package com.springboot2.htservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BoardControllerTest {
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
    public void boardDetail() throws Exception{
        //given
        Board saveBoard = boardRepository.save(Board.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long getId = saveBoard.getId();

        String url = "http://localhost:"+port+"/board/"+getId;

        //when
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        List<Board> all = boardRepository.findAll();
        assertThat(all.get(0).getViewCount()).isEqualTo(1L);
    }

    @Test
    public void boardUpdate() throws Exception{

    }
}
