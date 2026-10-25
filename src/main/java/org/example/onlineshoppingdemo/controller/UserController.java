package org.example.onlineshoppingdemo.controller;

import jakarta.annotation.Resource;
import org.example.onlineshoppingdemo.config.MyUser;
import org.example.onlineshoppingdemo.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    Map<Integer, MyUser> users = new HashMap<Integer, MyUser>();

    @Resource(name = "z3") // default user is z3
    MyUser defaultUser;

    @Resource
    JwtService jwtService;

    @PostMapping("/users")
    public String addUser(@RequestParam int id,
                          @RequestParam String name,
                          @RequestParam int age,
                          @RequestParam String email,
                          Map<String, Object> map) {
        // MyUser myUser = new MyUser(id, name, age, email);
        MyUser myUser = MyUser.builder()
                        .id(id)
                        .age(age)
                        .name(name)
                        .email(email)
                        .build();
        users.put(id, myUser); // store temporary info in the buffer
        map.put("abc", myUser); // put actual info in the View
        return "user_detail";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable int userId, Map<String, Object> map) {
        MyUser myUser = users.getOrDefault(userId, defaultUser);
        String token = jwtService.encryptToken(myUser);
        String decryptUserName = jwtService.decryptUserName(token);
        map.put("jwtToken", token);
        map.put("jwtUserName", decryptUserName);
        map.put("abc", myUser); // match the data name in the HTML
        return "user_detail"; // HTML file in View
    }

    @GetMapping("/users")
    public String getUser(@RequestParam String name, Map<String, Object> map) {
        for (MyUser myUser : users.values()) {
            if (myUser.getName().equals(name)) {
                map.put("abc", myUser);
                return "user_detail";
            }
        }
        map.put("abc", defaultUser);
        return "user_detail";
    }

//    public String getUsers(int userId) {
//        users.get(userId);
//    }
}
