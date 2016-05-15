package com.morder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by amis on 16-5-8.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/main.do")
    public String mainPage() throws Exception {
        return "/home/main";
    }



}
