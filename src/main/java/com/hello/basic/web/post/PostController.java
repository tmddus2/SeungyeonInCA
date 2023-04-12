package com.hello.basic.web.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    @GetMapping("/{postId}")
    public String posts(Model model, @PathVariable String postId) {
        model.addAttribute("postId", postId);
        return "travelPage/travel";
    }
}
