package com.springboot2.htservice.web;

import com.springboot2.htservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board")
    public String board(Model model/*@LoginUser SessionUser user*/){
        model.addAttribute("board", boardService.findAllDesc());
        /*if(user! =null) {
            model.addAttribute("useName", user.getName());
        }*/
        return "board";
    }



}
