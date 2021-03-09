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
public class UserRegister {

    private User user;
    private UserRepository userRepository;

    public UserRegister(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute(name = "user") User user1,
                                 Model model,
                                 HttpSession httpsession) {
        if (userRepository.findById(user1.getEmail()).isPresent()) {
            model.addAttribute("registerStatus", "duplicate");
            return "register";
        }
        httpsession.setAttribute("userEmail", user1.getEmail());
        httpsession.setMaxInactiveInterval(4 * 60 * 60);
        userRepository.save(user1);
        return "redirect:/index";
    }
}
