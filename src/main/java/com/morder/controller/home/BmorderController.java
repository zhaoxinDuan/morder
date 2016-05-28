package com.morder.controller.home;

import com.morder.controller.BaseController;
import com.morder.model.Bmorder;
import com.morder.model.Bmorderitem;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TcustomerService tcustomerService;

    @RequestMapping("/bmlist.do")
    public String bmlist() throws Exception {

        return "/home/bm/bmlist";
    }

    @RequestMapping("/bmlistdetail.do")
    public String bmlistdetail() throws Exception {

        return "/home/bm/bmlistdetail";
    }


    @RequestMapping("/bmindex.do")
    public String cusindex(ModelMap modelMap,Integer idbmorder,Integer iduser) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Tuser tUser = this.tuserService.selectByUname(user.getUsername());
        Bmorder bmorder = null;
        if(idbmorder!=null) {
            bmorder = bmorderService.selectByPrimaryKey(idbmorder);
        }
        modelMap.put("bmorder",bmorder);
        modelMap.put("iduser", iduser);
        return "/home/bm/bmindex";
    }


    @RequestMapping("/findAllBmorders.do")
    @ResponseBody
    public List findAllBmorders(Integer rows, Integer page) throws Exception {
        List list = this.bmorderService.findAllBmorders((page - 1) * rows, rows);
        return list;
    }


    @RequestMapping("/delBmorderById.do")
    @ResponseBody
    public String delBmorderById(Integer idbmorder) throws Exception {
        try {
            this.bmorderService.deleteByPrimaryKey(idbmorder);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/updateMorder.do")
    @ResponseBody
    public String updateMorder(Integer idbmorder,Integer bmstatus) throws Exception {
        Bmorder bmorder = new Bmorder();
        bmorder.setIdbmorder(idbmorder);
        bmorder.setBmstatus(2);
        try {
            this.bmorderService.saveSelective(bmorder);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/saveMorderInfo.do")
    @ResponseBody
    public Map saveMorderInfo(Bmorder record, Boolean ispost) throws Exception {
        Map resultMap = new HashMap();
        Integer idbmorder = null;
        if (ispost) {
            record.setBmstatus(1);
        }else{
            record.setBmstatus(0);
        }
        Tcustomer tcustomer = this.tcustomerService.selectByPrimaryKey(record.getTcustomerIdcustomer());
        record.setBmcusname(tcustomer.getCname());
        try {
            this.bmorderService.saveSelective(record);

            idbmorder = record.getIdbmorder();
            resultMap.put("idbmorder", idbmorder);
            resultMap.put("bmordernum", record.getBmordernum());
            resultMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("success", false);
        }
        return resultMap;
    }

    @RequestMapping("/delBmorderItemById.do")
    @ResponseBody
    public String delBmorderItemById(Integer idbmitem) throws Exception {
        try {
            Bmorderitem bmorderitem = this.bmorderService.selectItemByPrimaryKey(idbmitem);
            Bmorder bmorder = this.bmorderService.selectByPrimaryKey(bmorderitem.getBmorderIdbmorder());
            BigDecimal bmorderamount = bmorder.getBmorderamount().subtract(bmorderitem.getBmiamount());
            Bmorder bmorder1 = new Bmorder();
            bmorder1.setIdbmorder(bmorder.getIdbmorder());
            bmorder1.setBmorderamount(bmorderamount);
            this.bmorderService.deleteItemByPrimaryKey(idbmitem, bmorder1);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }


    @RequestMapping("/saveMorderItemInfo.do")
    @ResponseBody
    public String saveMorderItemInfo(Bmorderitem record,BigDecimal changebmorderamount) throws Exception {
        try {
            this.bmorderService.saveItemSelective(record, changebmorderamount);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }



    @RequestMapping("/findAllBmorderItems.do")
    @ResponseBody
    public List findAllBmorderItems(Integer rows, Integer page) throws Exception {
        return this.bmorderService.findAllBmorderitems((page - 1) * rows, rows);
    }


    @RequestMapping("/findItemsByIdbmorder.do")
    @ResponseBody
    public List findItemsByIdbmorder(Integer idbmorder) throws Exception {
        return this.bmorderService.findItemsByIdbmorder(idbmorder);
    }


}


