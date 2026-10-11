package org.example.onlineshoppingdemo.controller;

import org.example.onlineshoppingdemo.config.MyUser;
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

    @PostMapping("/users")
    public String addUser(@RequestParam int id,
                          @RequestParam String name,
                          @RequestParam int age,
                          @RequestParam String email,
                          Map<String, Object> map) {
        MyUser myUser = new MyUser(id, name, age, email);
        users.put(id, myUser); // store temporary info in the buffer
        map.put("abc", myUser); // put actual info in the View
        return "user_detail";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@RequestParam int userId, Map<String, Object> map) {
        MyUser myUser = users.get(userId);
        map.put("abc", myUser);
        return "user_detail";
    }

    @GetMapping("/users")
    public String getUser(@RequestParam String name, Map<String, Object> map) {
        for (MyUser myUser : users.values()) {
            if (myUser.getName().equals(name)) {
                map.put("abc", myUser);
                return "user_detail";
            }
        }
        return null;
    }

//    public String getUsers(int userId) {
//        users.get(userId);
//    }
}
