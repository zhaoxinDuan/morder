package com.morder.controller.system;

import com.morder.controller.BaseController;
import com.morder.model.Tuser;
import com.morder.service.TuserService;
import com.morder.utils.JSONResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String mainPage(ModelMap modelMap, Integer idunit) throws Exception {
        modelMap.put("idunit", idunit);
        return "/system/user/userindex";
    }

    @RequestMapping("/findAllUsers.do")
    @ResponseBody
    public List findAllUsers(Integer rows, Integer page) throws Exception {
        return this.tuserService.findAllUsers((page - 1) * rows, rows);
    }

    @RequestMapping("/findAllUnitUsers.do")
    @ResponseBody
    public List findAllUnitUsers(Integer rows, Integer page,Integer idunit) throws Exception {
        return this.tuserService.findAllUnitUsers((page - 1) * rows, rows,idunit);
    }

    @RequestMapping("/delUserById.do")
    @ResponseBody
    public String delUserById(Integer iduser) throws Exception {
        try {
            this.tuserService.deleteByPrimaryKey(iduser);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/userUpdateStatus.do")
    @ResponseBody
    public String userUpdateStatus(Integer iduser, Integer uisdel) throws Exception {
        try {
            this.tuserService.userUpdateStatus(iduser, uisdel);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/saveUserInfo.do")
    @ResponseBody
    public String saveUserInfo(Tuser tuser, Integer idunit) throws Exception {
        try {
            this.tuserService.saveSelective(tuser, idunit);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }


}
