package com.hello.basic.service;

import com.hello.basic.dto.*;
import com.hello.basic.entity.Post;
import com.hello.basic.entity.Travel;
import com.hello.basic.entity.User;
import com.hello.basic.repository.PostRepository;
import com.hello.basic.repository.TravelRepository;
import com.hello.basic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
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

    public SignInResponseDto signIn(SignInDto signInDto) {
        try {
            User user = userRepository.findByEmail(signInDto.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("해당 user가 없습니다."));

            return SignInResponseDto.builder()
                    .isSuccess(true)
                    .user(user)
                    .build();
        } catch (Exception e) {
            return SignInResponseDto.builder()
                    .isSuccess(false)
                    .user(null)
                    .build();
        }
    }

    public UserDto findUserById(Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 user가 없습니다."));
            return UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .build();
        } catch (Exception e) {
            return UserDto.builder()
                    .id(null)
                    .email(null)
                    .build();
        }
    }

    public List<PostDto> getPosts(Long userId) {
        List<Post> posts = postRepository.findAllByUser_Id(userId);
        List<PostDto> postDtos = new ArrayList<>();

        for(Post post : posts) {
            postDtos.add(
                    PostDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build()
            );
        }

        return postDtos;
    }
}
