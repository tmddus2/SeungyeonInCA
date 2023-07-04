package com.hello.basic.service;

import com.hello.basic.dto.PostDto;
import com.hello.basic.dto.TravelDto;
import com.hello.basic.entity.Post;
import com.hello.basic.entity.Travel;
import com.hello.basic.repository.PostRepository;
import com.hello.basic.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final TravelRepository travelRepository;
    private final S3Service s3Service;

    public PostDto savePost(TravelDto travelDto, Long id) {
        try {
            Travel savedTravel = travelRepository.save(
                    Travel.builder()
                            .userId(id)
                            .build());

            Post post = Post.builder()
                    .title(travelDto.getName())
                    .content(travelDto.getDescription())
                    .picture(s3Service.saveFile(travelDto.getFile()))
                    .userId(id)
                    .travelId(savedTravel.getId())
                    .build();
            Post savedPost = postRepository.save(post);

            return PostDto.builder()
                    .id(savedPost.getId())
                    .title(savedPost.getTitle())
                    .content(savedPost.getContent())
                    .picture(savedPost.getPicture())
                    .travelId(savedPost.getTravelId())
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
                    .travelId(post.getTravelId())
                    .build();
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public Long getTravelId(Long id) {
        PostDto post = findPostById(id);
        return post.getTravelId();
    }

}
