package com.morder.controller.home;

import com.github.pagehelper.PageInfo;
import com.morder.controller.BaseController;
import com.morder.model.Tcustomer;
import com.morder.model.Tuser;
import com.morder.service.TcustomerService;
import com.morder.service.TuserService;
import com.morder.utils.JSONPage;
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
@RequestMapping("/home/cus")
public class TcustomerController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TcustomerController.class);
    @Autowired
    private TcustomerService tcustomerService;
    @Autowired
    private TuserService tuserService;


    @RequestMapping("/cusindex.do")
    public String cusindex(ModelMap modelMap) throws Exception {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tuser tUser = this.tuserService.selectByUname(user.getUsername());
        modelMap.put("tUser",tUser);
        return "/home/cus/cusindex";
    }





    @RequestMapping("/findAllCustomers.do")
    @ResponseBody
    public JSONPage findAllCustomers(Integer rows, Integer page) throws Exception {
        PageInfo pageInfo = this.tcustomerService.findAllCustomers(page, rows);
        JSONPage jsonPage = new JSONPage();
        jsonPage.setRows(pageInfo.getList());
        jsonPage.setTotal(pageInfo.getTotal());
        return jsonPage;
    }

    @RequestMapping("/findAllCustomersNolimit.do")
    @ResponseBody
    public List findAllCustomersNolimit() throws Exception {
        return this.tcustomerService.findAllCustomersNolimit();
    }



    @RequestMapping("/delCusById.do")
    @ResponseBody
    public String delCusById(Integer idcustomer,Integer idcusapp,Integer idtcustax) throws Exception {
        try {
            this.tcustomerService.deleteByPrimaryKey(idcustomer, idcusapp, idtcustax);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/saveCusInfo.do")
    @ResponseBody
    public String saveCusInfo(Tcustomer record) throws Exception {
        try {
            this.tcustomerService.saveSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

}


