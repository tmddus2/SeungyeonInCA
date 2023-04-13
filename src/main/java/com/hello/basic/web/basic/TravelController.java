package com.hello.basic.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TravelController {

    @GetMapping("/post/{postId}/place")
    public String getPlace(@PathVariable String postId, Model model) {
        model.addAttribute("postId", postId);
        return "travelPage/place";
    }

    @GetMapping("/post/{postId}/photo")
    public String getPhoto(@PathVariable String postId, Model model) {
        model.addAttribute("postId", postId);
        return "travelPage/photo";
    }

    @GetMapping("/post/{postId}/cost")
    public String getCost(@PathVariable String postId, Model model) {
        model.addAttribute("postId", postId);
        return "travelPage/cost";
    }
}
