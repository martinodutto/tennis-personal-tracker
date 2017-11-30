package com.martinodutto.tpt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomePageController {

    @RequestMapping("/")
    public ModelAndView homeDispatcher() {
        return new ModelAndView("home");
    }
}
