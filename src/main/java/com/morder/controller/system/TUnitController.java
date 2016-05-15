package com.morder.controller.system;

import com.morder.controller.BaseController;
import com.morder.form.SampleUiTreeForm;
import com.morder.model.Tunits;
import com.morder.service.TunitsService;
import com.morder.utils.JSONResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amis on 16-5-8.
 */
@Controller
@RequestMapping("/sys")
public class TUnitController extends BaseController {

    @Autowired
    private TunitsService tunitsService;

    @RequestMapping("/unitindex.do")
    public String mainPage() throws Exception {
        return "/system/unit/unitindex";
    }

    @RequestMapping("/getUnitTree.do")
    @ResponseBody
    public List<SampleUiTreeForm> getUnitTree()throws Exception{
        List<Tunits> tunitsls = this.tunitsService.findAllUnits();
        List<SampleUiTreeForm> uiTreels = new ArrayList<SampleUiTreeForm>();
        SampleUiTreeForm uiTreeForm = null;
        for(Tunits tunit:tunitsls){
            uiTreeForm = new SampleUiTreeForm();
            uiTreeForm.setId(String.valueOf(tunit.getIdunit()));
            uiTreeForm.setPid(tunit.getPidunit());
//                uiTreeModel.setState("closed");
            uiTreeForm.setText(tunit.getUnitname());
            uiTreels.add(uiTreeForm);
        }
        return uiTreels;

    }

    @RequestMapping("/saveunit.do")
    @ResponseBody
    public String saveunit(Tunits tunits) throws Exception {
        try {
            this.tunitsService.save(tunits);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

    @RequestMapping("/removeUnitsById.do")
    @ResponseBody
    public String removeUnitsById(Integer idunit) throws Exception {
        try {
            this.tunitsService.deleteByPrimaryKey(idunit);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResultUtils.MSG_ERROR;
        }
        return JSONResultUtils.MSG_SUCCESS;
    }

}
