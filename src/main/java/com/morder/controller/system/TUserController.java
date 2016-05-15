package com.morder.controller.system;

import com.morder.controller.BaseController;
import com.morder.model.Tuser;
import com.morder.service.TuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by amis on 16-5-8.
 */
@Controller
@RequestMapping("/sys")
public class TUserController extends BaseController {
    @Autowired
    private TuserService tuserService;

    @RequestMapping("/userindex.do")
    public String mainPage() throws Exception {
        return "/system/userindex";
    }

    @RequestMapping("/findAllUsers.do")
    @ResponseBody
    public List findAllUsers(Integer rows, Integer page) throws Exception {
        return this.tuserService.findAllUsers((page - 1) * rows,rows);
    }


}
