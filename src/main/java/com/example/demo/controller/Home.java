package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Home {
    @GetMapping("/index")
    public String homepage() {

        return "index";
    }

    @PostMapping("/logout")
    public String logOut(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/index";
    }

}
