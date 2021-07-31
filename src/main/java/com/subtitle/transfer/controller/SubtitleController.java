package com.subtitle.transfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SubtitleController {
    @RequestMapping("/")
    public String start(){
        return "index";
    }
}
