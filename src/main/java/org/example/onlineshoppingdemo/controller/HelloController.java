package org.example.onlineshoppingdemo.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Resource
    GetSum  getSum;

    public HelloController() {
    }
    protected HelloController(GetSum getSum) {
        this.getSum = getSum;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "GET Hello World";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "POST Hello World";
    }

    @GetMapping("/echo/{para}")
    public String echo(@PathVariable String para) {
        return "ECHO from: " + para;
    }

    public int add(int a, int b) {
        return  getSum.add(a,b);
    }
}
