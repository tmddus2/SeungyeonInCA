package com.hello.basic.web.basic;

import com.hello.basic.dto.TravelDto;
import com.hello.basic.dto.UserDto;
import com.hello.basic.service.TravelService;
import com.hello.basic.service.UserService;
import com.hello.basic.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;
    private final UserService userService;

    @ResponseBody
    @GetMapping()
    public UserDto getRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute(SessionConst.SESSION_ID);
        UserDto user = userService.findUserById(id);
        return user;
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
