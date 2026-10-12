package org.example.onlineshoppingdemo.config;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean(name = "z3")
    public MyUser myUserProvider() { //default user
        return new MyUser(0, "NoBody", 10, "nobody@hotmail.com");
    }

    @Bean(name = "L4")
    public MyUser myUserL4Provider() { //default user
        return new MyUser(0, "L4", 18, "l4@hotmail.com");
    }
}
