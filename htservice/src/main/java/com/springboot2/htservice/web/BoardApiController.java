package com.springboot2.htservice.web;

import com.springboot2.htservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

}
