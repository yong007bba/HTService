package com.springboot2.htservice.web;


import com.springboot2.htservice.service.BoardService;
import com.springboot2.htservice.web.dto.BoardResponseDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/save")
    public String boardSave() {
        return "board-save";
    }
    @GetMapping("/board/{id}") //@pathVariable: 경로의 특정위치값이 고정되지 않고 달라질때 사용함
    public String boardDetail(@PathVariable Long id, Model model) {
        boardService.updateView(id);
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);
        return "board-detail";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable Long id, Model model) {
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);
        return "board-update";
    }
}
