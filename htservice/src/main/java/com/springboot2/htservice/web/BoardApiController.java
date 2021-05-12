package com.springboot2.htservice.web;

import com.springboot2.htservice.service.BoardService;
import com.springboot2.htservice.web.dto.BoardSaveRequestDto;
import com.springboot2.htservice.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/board")
    public Long save(@RequestBody BoardSaveRequestDto requestDto){

        return boardService.save(requestDto);
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/board/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }

}
