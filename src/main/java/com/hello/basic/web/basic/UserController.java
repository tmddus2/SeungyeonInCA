package com.hello.basic.web.basic;

import com.hello.basic.dto.SignInDto;
import com.hello.basic.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/sign-in")
    public String signIn(Model model){
        model.addAttribute("signInDto", new SignInDto());
        return "userPage/signIn";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("signUpDto", new SignUpDto());
        return "userPage/signUp";
    }

    @PostMapping("/sign-up")
    public String registerUser(@ModelAttribute("signUpDto") SignUpDto signUpDto) {
        System.out.println("signUpDto = " + signUpDto.toString());
        return "redirect:/sign-in";
    }

    @PostMapping("/sign-in")
    public String login(@ModelAttribute("signInDto") SignInDto signInDto) {
        System.out.println("signInDto = " + signInDto);
        return "redirect:/";
    }
}
