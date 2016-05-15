package com.morder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by amis on 16-5-9.
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/login.do")
    public String login(String error, Model model) {
        logger.info("login");
        model.addAttribute("error", error);
        return "/index";
    }
}
