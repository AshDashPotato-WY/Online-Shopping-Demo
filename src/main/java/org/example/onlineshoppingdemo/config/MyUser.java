package org.example.onlineshoppingdemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyUser {
    int id;
    String name;
    int age;
    String email;
}
