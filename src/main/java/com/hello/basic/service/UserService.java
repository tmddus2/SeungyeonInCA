package com.hello.basic.service;

import com.hello.basic.dto.SignUpDto;
import com.hello.basic.dto.SignUpResponseDto;
import com.hello.basic.entity.User;
import com.hello.basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(SignUpDto signUpDto) {
        try{
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
        } catch (Exception e){
            return SignUpResponseDto.builder()
                    .id(null)
                    .email(null)
                    .build();
        }

    }
}
