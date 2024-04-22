package com.estsoft.blogproject.controller;

import com.estsoft.blogproject.domain.AddUserRequest;

import com.estsoft.blogproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signup(@ModelAttribute AddUserRequest request){   //폼데이터를 받을 때는 @RequestBody를 안 쓴다.
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
}
