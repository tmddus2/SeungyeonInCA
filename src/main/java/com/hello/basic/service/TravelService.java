package com.hello.basic.service;

import com.hello.basic.dto.TravelDto;
import com.hello.basic.entity.Travel;
import com.hello.basic.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public TravelDto findTravelById(Long id) {
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Travel이 없습니다."));

        return TravelDto.builder()
                .id(travel.getId())
                .build();
    }

}
