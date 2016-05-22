package com.morder.controller.home;

import com.morder.controller.BaseController;
import com.morder.service.TuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by amis on 16-5-8.
 */
@Controller
@RequestMapping("/home")
public class THomeController extends BaseController{
    @Autowired
    private TuserService tuserService;
    @RequestMapping("/main.do")
    public String mainPage() throws Exception {
        return "/home/main";
    }

    @RequestMapping("/findAllUsersNoLimit.do")
    @ResponseBody
    public List findAllUsersNoLimit(){
        return this.tuserService.findAllUsersNoLimit();
    }


}
