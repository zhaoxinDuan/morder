package com.morder.controller;

import com.morder.service.TuserService;
import com.morder.utils.JSONResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by amis on 16-5-9.
 */
@Controller
@RequestMapping("/sys")
public class LoginController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager myAuthenticationManager;
    @RequestMapping("/login.do")
    @ResponseBody
    public String login(String j_username,String j_password,String error, Model model,HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(j_username, j_password);

		try {
            Authentication authentication = myAuthenticationManager.authenticate(authRequest); //调用loadUserByUsername
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
            return JSONResultUtils.MSG_SUCCESS;
        } catch (AuthenticationException ex) {
            model.addAttribute("error", error);
            logger.error(j_username + ",用户登录错误！登录IP"+request.getRemoteAddr());
            return JSONResultUtils.MSG_ERROR;
        }
    }

    @RequestMapping("/indexPage.do")
    public String indexPage(){
        return "/index";
    }

}
