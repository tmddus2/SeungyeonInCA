package com.hello.basic.repository;

import com.hello.basic.entity.Post;
import com.hello.basic.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_Id(Long id);
}
