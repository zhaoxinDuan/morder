package com.morder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by amis on 16-5-8.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index.htmls")
    public String index() throws Exception {
        return "/home/login";
    }
    @RequestMapping("/login.htmls")
    public String login(String username, String password) throws Exception {
        return "";
    }

    public String logout() throws Exception {
        return "";
    }

}
