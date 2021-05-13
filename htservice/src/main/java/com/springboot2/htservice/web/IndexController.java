package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.config.auth.dto.SessionUser;
import com.springboot2.htservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board")
    public String board(Model model, @LoginUser SessionUser user, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("board", boardService.getBoardList(pageable));
        model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("check",boardService.getListCheck(pageable));
        if(user != null) {
            System.out.println("user.getName() = " + user.getName());
            model.addAttribute("userName", user.getName());
        }
        return "board";
    }

}
