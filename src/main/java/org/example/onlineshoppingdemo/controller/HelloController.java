package org.example.onlineshoppingdemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    GetSum  getSum;

    public HelloController() {
        getSum = new GetSum();
    }

    public HelloController(GetSum getSum) {
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
