package com.morder.controller.home;

import com.github.pagehelper.PageInfo;
import com.morder.component.ExcelModel;
import com.morder.component.ModifyAndExportExcel;
import com.morder.component.TemplateConfig;
import com.morder.controller.BaseController;
import com.morder.form.BmorderSearchForm;
import com.morder.model.Bmorder;
import com.morder.service.BmorderService;
import com.morder.service.TcustomerService;
import com.morder.utils.ConstantUtils;
import com.morder.component.ExportExcel;
import com.morder.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amis on 16-5-29.
 */
@Controller
@RequestMapping("/home/execl")
public class ExcelController extends BaseController {
    @Autowired
    private BmorderService bmorderService;
    @Autowired
    private TcustomerService tcustomerService;
    @Autowired
    private TemplateConfig templateConfig;
    @Autowired
    private ExportExcel exportExcel;
    @Autowired
    private ModifyAndExportExcel modifyAndExportExcel;

    @RequestMapping("/createptemplate.do")
    public void createptemplate(Integer idbmitem,Integer iduser, HttpServletResponse response) throws Exception {
        String filepath = templateConfig.getExecltemplatepath();
        Map resultmap = this.bmorderService.findAllBmordersByItemid(idbmitem);
        List<ExcelModel> excelModels = new ArrayList<ExcelModel>();

        //订单编号
        excelModels.add(new ExcelModel(1,11, Utils.getObjectToString(resultmap.get("bmordernum"))));
        //客户名称
        excelModels.add(new ExcelModel(2,2, Utils.getObjectToString(resultmap.get("cname"))));
        //联系电话
        excelModels.add(new ExcelModel(2,7, Utils.getObjectToString(resultmap.get("camphone"))));
        //开单日期
        excelModels.add(new ExcelModel(2,11, Utils.getDataObjectToStringCN(resultmap.get("bmbillingdate"))));
        //名称
        excelModels.add(new ExcelModel(3,1, Utils.getObjectToString(resultmap.get("bmiproname"))));
        //规格
        excelModels.add(new ExcelModel(3,8, Utils.getObjectToString(resultmap.get("bmorderitemcol"))));
        //交货数量
        excelModels.add(new ExcelModel(3,11, Utils.getObjectToString(resultmap.get("bminum"))));
        //外发单号
        excelModels.add(new ExcelModel(4,2, Utils.getObjectToString(resultmap.get("bmioutternum"))));
        //交货日期
        excelModels.add(new ExcelModel(4,8, Utils.getDataObjectToStringCN(resultmap.get("bmdeliverydate"))));
        //打包要求
        excelModels.add(new ExcelModel(4,11, Utils.getObjectToString(resultmap.get("bmipacreq"))));
        //产品类别
        excelModels.add(new ExcelModel(5,1, ConstantUtils.protypeMap.get(Utils.getObjectToInteger(resultmap.get("bmiprotype")))));

        //备注
        excelModels.add(new ExcelModel(6,1, Utils.getObjectToString(resultmap.get("bmcomments"))));
        //开单人
        excelModels.add(new ExcelModel(22,2, Utils.getObjectToString(resultmap.get("ownername"))));

        modifyAndExportExcel.modifyExcel(response,filepath,"ptemplate.xls",excelModels,iduser);

    }


    @RequestMapping("/createstemplate.do")
    public void createstemplate(String idbmitems,Integer iduser, HttpServletResponse response) throws Exception {
        String filepath = templateConfig.getExecltemplatepath();
        Map resultmap = this.bmorderService.findAllBmordersByItemid(idbmitem);
        List<ExcelModel> excelModels = new ArrayList<ExcelModel>();

        //订单编号
        excelModels.add(new ExcelModel(1,11, Utils.getObjectToString(resultmap.get("bmordernum"))));
        //客户名称
        excelModels.add(new ExcelModel(2,2, Utils.getObjectToString(resultmap.get("cname"))));
        //联系电话
        excelModels.add(new ExcelModel(2,7, Utils.getObjectToString(resultmap.get("camphone"))));
        //开单日期
        excelModels.add(new ExcelModel(2,11, Utils.getDataObjectToStringCN(resultmap.get("bmbillingdate"))));
        //名称
        excelModels.add(new ExcelModel(3,1, Utils.getObjectToString(resultmap.get("bmiproname"))));
        //规格
        excelModels.add(new ExcelModel(3,8, Utils.getObjectToString(resultmap.get("bmorderitemcol"))));
        //交货数量
        excelModels.add(new ExcelModel(3,11, Utils.getObjectToString(resultmap.get("bminum"))));
        //外发单号
        excelModels.add(new ExcelModel(4,2, Utils.getObjectToString(resultmap.get("bmioutternum"))));
        //交货日期
        excelModels.add(new ExcelModel(4,8, Utils.getDataObjectToStringCN(resultmap.get("bmdeliverydate"))));
        //打包要求
        excelModels.add(new ExcelModel(4,11, Utils.getObjectToString(resultmap.get("bmipacreq"))));
        //产品类别
        excelModels.add(new ExcelModel(5,1, ConstantUtils.protypeMap.get(Utils.getObjectToInteger(resultmap.get("bmiprotype")))));

        //备注
        excelModels.add(new ExcelModel(6,1, Utils.getObjectToString(resultmap.get("bmcomments"))));
        //开单人
        excelModels.add(new ExcelModel(22,2, Utils.getObjectToString(resultmap.get("ownername"))));

        modifyAndExportExcel.modifyExcel(response,filepath,"ptemplate.xls",excelModels,iduser);

    }



    @RequestMapping("/exportBmlistDetail.do")
    public void exportBmlistDetail(BmorderSearchForm bmorderSearchForm, HttpServletResponse response) throws Exception {
        String consql = bmorderSearchForm.getBuilderSql();
        String filters = (!StringUtils.isEmpty(consql) ? " where 1=1 " + consql : "");
//        String filters = "";
        PageInfo pageInfo = this.bmorderService.findAllBmordersByDetails(1, 60000, filters);
        BigDecimal totalamount = this.bmorderService.selectSumBmorderamount(filters);
        Integer totalorder = this.bmorderService.selectBmorderCount(filters);
        String title = "订单详情";


        String[] rowsName = new String[]{"序号", "订单编号", "客户名称", "开单日期", "交货日期", "产品规格"
                , "产品名称", "包装要求", "产品类型", "数量", "金额", "额外费用", "订单金额", "备注", "订单状态", "负责人"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        List datalist = pageInfo.getList();
        Map rowmap = null;
        for (int i = 0; i < datalist.size(); i++) {
            rowmap = (Map) datalist.get(i);
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = rowmap.get("bmordernum");
            objs[2] = rowmap.get("bmcusname");
            objs[3] = rowmap.get("bmbillingdate");
            objs[4] = rowmap.get("bmbillingdate");
            objs[5] = rowmap.get("bmorderitemcol");
            objs[6] = rowmap.get("bmiproname");
            objs[7] = rowmap.get("bmpacreq");

            objs[8] = ConstantUtils.protypeMap.get(rowmap.get("bmiprotype"));
            objs[9] = rowmap.get("bminum");
            objs[10] = rowmap.get("bmiamount");
            objs[11] = rowmap.get("bmaddcosts");
            objs[12] = rowmap.get("bmorderamount");
            objs[13] = rowmap.get("bmcomments");

            objs[14] = ConstantUtils.statusMap.get(rowmap.get("bmstatus"));
            objs[15] = rowmap.get("ownername");

            dataList.add(objs);
        }

//        （订单数："+totalorder+" ,产品数量："+pageInfo.getTotal()+" ,订单金额："+totalamount+")"
        Map<String, Object> othermap = new HashMap<String, Object>();
        othermap.put("订单数", totalorder);
        othermap.put("产品数量", pageInfo.getTotal());
        othermap.put("订单金额", totalamount);
        try {
            exportExcel.export(title, rowsName, dataList, othermap, response);
        } catch (Exception e) {
            throw e;
        }
    }
}
