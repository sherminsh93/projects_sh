package com.example.demo;

import com.example.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBean {
    @Bean
    public User getUser() {
        return new User();
    }


}
