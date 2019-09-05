package com.java.controller;

import com.java.service.NextService;
import com.java.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/number")
public class RandomController {

    @Autowired
    private RandomService randomService;

    @Autowired
    private NextService nextService;

    @GetMapping("/random")
    public String random(Model model){
        model.addAttribute("random_num", randomService.getRandomNumber());
        return "random";
    }

    @GetMapping("/next")
    public String next(Model model){
        model.addAttribute("next", nextService.getNextNumber());
        return "next";
    }

}
