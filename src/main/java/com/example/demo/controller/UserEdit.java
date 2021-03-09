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
public class UserEdit {
    private UserRepository userRepository;
    private User user;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/edit")
    public String editPage(HttpSession httpSession, Model model) {
        if (userRepository.findById(String.valueOf(httpSession.getAttribute("userEmail"))).isPresent()) {
            user = userRepository.findById(String.valueOf(httpSession.getAttribute("userEmail"))).get();
            model.addAttribute("user", user);
            return "edit";

        }
        return "redirect:/index";
    }


    @PostMapping("/edit")
    public String doedit(@ModelAttribute(name = "user") User user1,
                         Model model,
                         HttpSession httpsession) {

        userRepository.save(user1);
        model.addAttribute("editStatus", "done");
        return "edit";

    }
}
