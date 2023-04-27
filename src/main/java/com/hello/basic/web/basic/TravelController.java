package com.hello.basic.web.basic;

import com.hello.basic.dto.TravelDto;
import com.hello.basic.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @ResponseBody
    @GetMapping()
    public String getRequest() {
        return "hello";
    }

    @GetMapping("/{postId}/place")
    public String getPlace(@PathVariable String postId, Model model) {
        model.addAttribute("postId", postId);
        return "travelPage/place";
    }

    @GetMapping("/{postId}/photo")
    public String getPhoto(@PathVariable String postId, Model model) {
        model.addAttribute("postId", postId);
        return "travelPage/photo";
    }

    @GetMapping("/{postId}/cost")
    public String getCost(@PathVariable String postId, Model model) {
        model.addAttribute("postId", postId);
        return "travelPage/cost";
    }

    @GetMapping("/post")
    public String addPost(Model model) {
        model.addAttribute("travel", new TravelDto());
        return "travelPage/addTravel";
    }

    @PostMapping("/post")
    public String savePost(@ModelAttribute TravelDto travelDto, MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        String img = travelService.saveFile(file);

        redirectAttributes.addAttribute("postId", 1);
        redirectAttributes.addAttribute("name", travelDto.getName());
        redirectAttributes.addAttribute("description", travelDto.getDescription());
        redirectAttributes.addAttribute("file", img);

        return "redirect:/post/1";
    }

}
