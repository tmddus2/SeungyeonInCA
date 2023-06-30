package com.hello.basic.repository;

import com.hello.basic.entity.Post;
import com.hello.basic.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    Optional<Post> findById(Long id);
    List<Post> findAllByUser_Id(Long id);
}
