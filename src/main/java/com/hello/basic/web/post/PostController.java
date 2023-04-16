package com.hello.basic.web.post;

import com.hello.basic.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final S3Uploader s3Uploader; // for test

    @GetMapping("/{postId}")
    public String posts(Model model, @PathVariable String postId, @RequestParam HashMap<String,String> paramMap) {
        model.addAttribute("postId", postId);
        model.addAttribute("name", paramMap.get("name"));
        model.addAttribute("description", paramMap.get("description"));
        model.addAttribute("file", paramMap.get("file"));
        return "travelPage/home";
    }
}
