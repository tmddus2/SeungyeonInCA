package com.hello.basic.repository;

import com.hello.basic.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findAllByUser_Id(Long id);
}
