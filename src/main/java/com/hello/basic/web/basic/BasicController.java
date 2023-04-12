package com.hello.basic.web.basic;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BasicController {
    @GetMapping
    public String getPosts(Model model) {
        List<Post> list = new ArrayList<>();
        list.add(new Post("title1", "content1", "1"));
        list.add(new Post("title2", "content2", "2"));
        list.add(new Post("title3", "content3", "3"));
        list.add(new Post("title4", "content4", "4"));
        list.add(new Post("title5", "content5", "5"));
        model.addAttribute("posts", list);
        return "mainPage/main";
    }

    @Data
    static class Post {
        private String title;
        private String content;
        private String url;

        public Post(String title, String content, String url) {
            this.title = title;
            this.content = content;
            this.url = url;
        }
    }
}


