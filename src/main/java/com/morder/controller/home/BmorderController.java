package com.morder.controller.home;

import com.morder.controller.BaseController;
import com.morder.model.Bmorder;
import com.morder.model.Tcustomer;
import com.morder.model.Tuser;
import com.morder.service.BmorderService;
import com.morder.service.TcustomerService;
import com.morder.service.TuserService;
import com.morder.utils.JSONResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by amis on 16-5-22.
 */
@Controller
@RequestMapping("/home/bm")
public class BmorderController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BmorderController.class);

    @Autowired
    private TuserService tuserService;
    @Autowired
    private BmorderService bmorderService;

    @RequestMapping("/bmindex.do")
    public String cusindex(ModelMap modelMap) throws Exception {
//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Tuser tUser = this.tuserService.selectByUname(user.getUsername());
//        modelMap.put("tUser",tUser);
        return "/home/bm/bmindex";
    }


    @RequestMapping("/findAllBmorders.do")
    @ResponseBody
    public List findAllBmorders(Integer rows, Integer page) throws Exception {
        return this.bmorderService.findAllBmorders((page - 1) * rows, rows);
    }


    @RequestMapping("/delBmorderById.do")
    @ResponseBody
    public String delCusById(Integer idbmorder) throws Exception {
        try {
            this.bmorderService.deleteByPrimaryKey(idbmorder);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/saveOrderInfo.do")
    @ResponseBody
    public String saveCusInfo(Bmorder record) throws Exception {
        try {
            this.bmorderService.saveSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

}


