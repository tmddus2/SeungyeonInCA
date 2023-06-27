package com.hello.basic.web.basic;

import com.hello.basic.dto.PostDto;
import com.hello.basic.dto.UserDto;
import com.hello.basic.service.UserService;
import com.hello.basic.web.SessionConst;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BasicController {

    private final UserService userService;

    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.SESSION_ID, required = false) Long userId, Model model) {
        if (userId == null) {
            log.info("user info is null");
            return "mainPage/main";
        }

        UserDto userDto = userService.findUserById(userId);
        if (userDto == null) {
            log.info("no user");
            return "mainPage/main";
        }
        model.addAttribute("user", userDto);

        List<PostDto> posts = userService.getPosts(userId);
        model.addAttribute("posts", posts);

        return "mainPage/loginMain";
    }

}


