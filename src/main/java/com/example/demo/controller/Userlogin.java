package com.example.demo.controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Userlogin {
    private User user;
    private UserRepository userRepository;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(Model model) {


        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "user") User user1, Model model, HttpSession httpsession
    ) {

        if (userRepository.findById(user1.getEmail()).isPresent()) {
            user = userRepository.findById(user1.getEmail()).get();
            if (user.getPassword().equals(user1.getPassword())) {
                httpsession.setAttribute("userEmail", user1.getEmail());
                model.addAttribute("accountname", user.getName());
                return "/index";
            } else {
                model.addAttribute("loginstate", "wrongPass");
                return "/login";
            }

        } else {
            model.addAttribute("loginstate", "notRegistered");
            return "/index";
        }

    }


}





