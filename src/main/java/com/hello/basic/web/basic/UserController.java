package com.hello.basic.web.basic;

import com.hello.basic.dto.SignInDto;
import com.hello.basic.dto.SignUpDto;
import com.hello.basic.dto.SignUpResponseDto;
import com.hello.basic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    @ResponseBody
    @PostMapping("/sign-up")
    public SignUpResponseDto registerUser(@RequestBody SignUpDto signUpDto) {
        log.info("sign up controller");
        SignUpResponseDto signUpResponseDto = userService.signUp(signUpDto);
        //return "redirect:/sign-in";
        return signUpResponseDto;
    }

    @PostMapping("/sign-in")
    public String login(@ModelAttribute("signInDto") SignInDto signInDto) {
        System.out.println("signInDto = " + signInDto);
        return "redirect:/";
    }
}
