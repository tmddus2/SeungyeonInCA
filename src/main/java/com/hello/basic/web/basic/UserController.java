package com.hello.basic.web.basic;

import com.hello.basic.dto.SignInDto;
import com.hello.basic.dto.SignInResponseDto;
import com.hello.basic.dto.SignUpDto;
import com.hello.basic.dto.SignUpResponseDto;
import com.hello.basic.service.UserService;
import com.hello.basic.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @PostMapping("/sign-up")
    public String registerUser(@RequestBody SignUpDto signUpDto) {
        SignUpResponseDto signUpResponseDto = userService.signUp(signUpDto);
        return "mainPage/main";
    }

    @PostMapping("/sign-in")
    public String login(SignInDto signInDto, HttpServletRequest request) {
        SignInResponseDto signInResponseDto = userService.signIn(signInDto);
        if (signInResponseDto.isSuccess()) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.SESSION_ID, signInResponseDto.getUser().getId());
            return "redirect:/";
        }

        return "redirect:"+"userPage/signIn";
    }

    @PostMapping("/sign-out")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
