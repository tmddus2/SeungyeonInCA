package com.hello.basic.web.basic;

import com.hello.basic.dto.TravelDto;
import com.hello.basic.service.PostService;
import com.hello.basic.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/place")
    public String getPlacePage(@RequestParam Long id, Model model) {
        TravelDto travel = travelService.findTravelById(id);
        model.addAttribute("travel", travel);
        return "travelPage/place";
    }

    @GetMapping("/photo")
    public String getPhotoPage(@RequestParam Long id, Model model) {
        TravelDto travel = travelService.findTravelById(id);
        model.addAttribute("travel", travel);
        return "travelPage/photo";
    }

    @GetMapping("/cost")
    public String getCostPage(@RequestParam Long id, Model model) {
        TravelDto travel = travelService.findTravelById(id);
        model.addAttribute("travel", travel);
        return "travelPage/cost";
    }

}
