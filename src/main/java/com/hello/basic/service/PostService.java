package com.hello.basic.service;

import com.hello.basic.dto.PostDto;
import com.hello.basic.dto.TravelDto;
import com.hello.basic.entity.Post;
import com.hello.basic.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final S3Service s3Service;

    public PostDto savePost(TravelDto travelDto, Long id) {
        try {
            Post post = Post.builder()
                    .title(travelDto.getName())
                    .content(travelDto.getDescription())
                    .picture(s3Service.saveFile(travelDto.getFile()))
                    .userId(id)
                    .build();
            Post savedPost = postRepository.save(post);

            return PostDto.builder()
                    .id(savedPost.getId())
                    .title(savedPost.getTitle())
                    .content(savedPost.getContent())
                    .picture(savedPost.getPicture())
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public PostDto findPostById(Long id) {
        try {
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 Post가 없습니다."));
            return PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .picture(post.getPicture())
                    .build();
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
