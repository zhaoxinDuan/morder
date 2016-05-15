package com.morder.controller.system;

import com.morder.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by amis on 16-5-8.
 */
@Controller
@RequestMapping("/sys")
public class TUserController extends BaseController {


    @RequestMapping("/userindex.do")
    public String mainPage() throws Exception {
        return "/system/userindex";
    }



}
