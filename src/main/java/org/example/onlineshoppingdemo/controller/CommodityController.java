package org.example.onlineshoppingdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommodityController {

    @RequestMapping("/addItem")
    public String addCommodity(){
        return "add_commodity";
    }
}
