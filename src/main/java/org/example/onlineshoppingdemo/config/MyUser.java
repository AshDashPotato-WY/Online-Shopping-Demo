package org.example.onlineshoppingdemo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MyUser {
    int id;
    String name;
    int age;
    String email;
}
