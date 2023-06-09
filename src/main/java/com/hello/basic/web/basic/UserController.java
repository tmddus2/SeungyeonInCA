package com.hello.basic.web.basic;

import com.hello.basic.dto.SignInDto;
import com.hello.basic.dto.SignUpDto;
import com.hello.basic.dto.SignUpResponseDto;
import com.hello.basic.service.UserService;
import com.hello.basic.web.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtToken> signIn(@RequestBody SignInDto signInDto){
        JwtToken token = userService.login(
                signInDto.getEmail(),
                passwordEncoder.encode(signInDto.getPassword())
        );
        return ResponseEntity.ok(token);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpDto signUpDto){
        SignUpResponseDto signUpResponseDto = userService.register(signUpDto);
        return ResponseEntity.ok(signUpResponseDto);
    }
}
