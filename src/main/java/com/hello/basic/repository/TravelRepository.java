package com.hello.basic.repository;

import com.hello.basic.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    @Override
    Optional<Travel> findById(Long id);
}
