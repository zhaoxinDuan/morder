package com.morder.controller.system;

import com.github.pagehelper.PageInfo;
import com.morder.controller.BaseController;
import com.morder.model.Tuser;
import com.morder.service.TuserService;
import com.morder.utils.JSONPage;
import com.morder.utils.JSONResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public JSONPage findAllUsers(Integer rows, Integer page) throws Exception {
        PageInfo pageInfo = this.tuserService.findAllUsers(page, rows);
        JSONPage jsonPage = new JSONPage();
        jsonPage.setRows(pageInfo.getList());
        jsonPage.setTotal(pageInfo.getTotal());

        return jsonPage;
    }

    @RequestMapping("/findAllUnitUsers.do")
    @ResponseBody
    public JSONPage findAllUnitUsers(Integer rows, Integer page,Integer idunit) throws Exception {
        PageInfo pageInfo = this.tuserService.findAllUnitUsers(page, rows, idunit);
        JSONPage jsonPage = new JSONPage();
        jsonPage.setRows(pageInfo.getList());
        jsonPage.setTotal(pageInfo.getTotal());

        return jsonPage;
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

@RequestMapping("/modifypwd.do")
    @ResponseBody
    public String modifypwd(Integer iduser,String oldupwd,String newupwd1,String newupwd2) throws Exception {
        try {
            Tuser tUser = this.tuserService.selectByPrimaryKey(iduser);
            if(tUser==null||oldupwd.equals(newupwd1)||!newupwd1.equals(newupwd2))return JSONResultUtils.MSG_ERROR;
            Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
            String opwd = md5PasswordEncoder.encodePassword(oldupwd,null);
            if(opwd.equals(tUser.getUpwd())){
                Tuser tUser2 = new Tuser();
                tUser2.setIduser(iduser);
                tUser2.setUpwd(newupwd1);
                this.tuserService.saveSelective(tUser2,null);
            }else{
                return JSONResultUtils.MSG_ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }


}
