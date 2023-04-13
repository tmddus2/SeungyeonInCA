package com.hello.basic.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/sign-in")
    public String signIn(){
        return "userPage/signIn";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "userPage/signUp";
    }
}
