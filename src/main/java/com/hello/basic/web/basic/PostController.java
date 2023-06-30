package com.hello.basic.web.basic;

import com.hello.basic.dto.PostDto;
import com.hello.basic.dto.TravelDto;
import com.hello.basic.service.PostService;
import com.hello.basic.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String getAddPost(Model model) {
        model.addAttribute("travel", new TravelDto());
        return "travelPage/addTravel";
    }

    @PostMapping
    public String addPost(@Login Long id, TravelDto travelDto) {
        if (id == null) {
            return "redirect:/post";
        }
        PostDto postDto = postService.savePost(travelDto, id);
        if (postDto == null) {
            return "redirect:/post";
        }
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/{postId}")
    public String getPostPage(@PathVariable Long postId) {
        return postService.findPostById(postId).toString();
    }

}
