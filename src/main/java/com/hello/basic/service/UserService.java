package com.hello.basic.service;

import com.hello.basic.dto.SignUpDto;
import com.hello.basic.dto.SignUpResponseDto;
import com.hello.basic.entity.User;
import com.hello.basic.repository.UserRepository;
import com.hello.basic.web.jwt.JwtToken;
import com.hello.basic.web.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public JwtToken login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return jwtTokenProvider.generateToken(authenticationToken);
    }

    public SignUpResponseDto register(SignUpDto signUpDto) {
        try {
            if (!signUpDto.getPassword().equals(signUpDto.getConfirmedPassword())) {
                throw new Exception("password와 confirmedPassword가 다릅니다.");
            }
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .build();
            User savedUser = userRepository.save(user);

            return SignUpResponseDto.builder()
                    .id(savedUser.getId())
                    .email(savedUser.getEmail())
                    .build();
        } catch (Exception e) {
            return SignUpResponseDto.builder()
                    .id(null)
                    .email(null)
                    .build();
        }
    }
}
