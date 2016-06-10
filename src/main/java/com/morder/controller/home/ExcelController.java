package com.morder.controller.home;

import com.github.pagehelper.PageInfo;
import com.morder.component.ExcelModel;
import com.morder.component.ModifyAndExportExcel;
import com.morder.component.TemplateConfig;
import com.morder.controller.BaseController;
import com.morder.form.BmorderSearchForm;
import com.morder.model.Bmaddcosts;
import com.morder.model.Bmmarker;
import com.morder.service.BmmarkerService;
import com.morder.service.BmorderService;
import com.morder.utils.ConstantUtils;
import com.morder.component.ExportExcel;
import com.morder.utils.NumUtil;
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
    private TemplateConfig templateConfig;
    @Autowired
    private ExportExcel exportExcel;
    @Autowired
    private ModifyAndExportExcel modifyAndExportExcel;
    @Autowired
    private BmmarkerService bmmarkerService;

    @RequestMapping("/createptemplate.do")
    public void createptemplate(Integer idbmorder, Integer iduser, HttpServletResponse response) throws Exception {
        String filepath = templateConfig.getExecltemplatepath();


        List<Map> resultls = this.bmorderService.findAllBmordersByMorderid(idbmorder);
        List<List<ExcelModel>> lists = new ArrayList<List<ExcelModel>>();
        List<ExcelModel> excelModels = null;
        String num = null;

        for (Map resultmap : resultls) {

            excelModels = new ArrayList<ExcelModel>();
            num = Utils.getObjectToString(resultmap.get("bmordernum"));

            //订单编号
            excelModels.add(new ExcelModel(1, 11, Utils.getObjectToString(resultmap.get("bmordernum"))));
            //客户名称
            excelModels.add(new ExcelModel(2, 2, Utils.getObjectToString(resultmap.get("cname"))));
            //联系电话
            excelModels.add(new ExcelModel(2, 7, Utils.getObjectToString(resultmap.get("camphone"))));
            //开单日期
            excelModels.add(new ExcelModel(2, 11, Utils.getDataObjectToStringCN(resultmap.get("bmbillingdate"))));
            //名称
            excelModels.add(new ExcelModel(3, 1, Utils.getObjectToString(resultmap.get("bmiproname"))));
            //规格
            excelModels.add(new ExcelModel(3, 8, Utils.getObjectToString(resultmap.get("bmorderitemcol"))));
            //交货数量
            excelModels.add(new ExcelModel(3, 11, Utils.getObjectToString(resultmap.get("bminum"))));
            //外发单号
            excelModels.add(new ExcelModel(4, 2, Utils.getObjectToString(resultmap.get("bmioutternum"))));
            //交货日期
            excelModels.add(new ExcelModel(4, 8, Utils.getDataObjectToStringCN(resultmap.get("bmdeliverydate"))));
            //打包要求
            excelModels.add(new ExcelModel(4, 11, Utils.getObjectToString(resultmap.get("bmipacreq"))));
            //产品类别
            excelModels.add(new ExcelModel(5, 1, ConstantUtils.protypeMap.get(Utils.getObjectToInteger(resultmap.get("bmiprotype")))));

            //备注
            excelModels.add(new ExcelModel(6, 1, Utils.getObjectToString(resultmap.get("bmcomments")), true));
            //开单人
            excelModels.add(new ExcelModel(22, 2, Utils.getObjectToString(resultmap.get("ownername"))));

            lists.add(excelModels);
        }

        modifyAndExportExcel.modifyExcel(response, filepath, "ptemplate.xls", lists, iduser, num);

    }


    @RequestMapping("/createstemplate.do")
    public void createstemplate(String idbmorders, Integer iduser, HttpServletResponse response) throws Exception {
        String filepath = templateConfig.getExecltemplatepath();
        String[] arridorders = idbmorders.split("\\|");
        List<ExcelModel> excelModels = new ArrayList<ExcelModel>();
        int count = 4;
        BigDecimal bmiamount = new BigDecimal(0);
        String strbmiamount = null;
        List<List<ExcelModel>> lists = new ArrayList<List<ExcelModel>>();
        List<Map> resultls = null;
        String bmcusname = null;
        String bmdeliverydate = null;
        Integer bmdenum = null;
        String addCostsDesc = "";
        String productname=null;
        List<Integer> emptyDeNumls = new ArrayList<Integer>();
        for (String stridorder : arridorders) {
            if (!StringUtils.isEmpty(stridorder)) {
                resultls = this.bmorderService.findBmorderAndItemByMorderid(Integer.parseInt(stridorder));
                for (Map resultmap : resultls) {
                    if (StringUtils.isEmpty(bmcusname)) {
                        bmcusname = Utils.getObjectToString(resultmap.get("bmcusname"));
                    }
                    if (StringUtils.isEmpty(bmdeliverydate)) {
                        bmdeliverydate = Utils.getDataObjectToStringCN(resultmap.get("bmdeliverydate"));
                    }
                    if (bmdenum == null) {
                        bmdenum = Utils.getObjectToInteger(resultmap.get("bmdenum"));
                    }
                    if (Utils.getObjectToInteger(resultmap.get("bmdenum")) == null) {
                        emptyDeNumls.add(Integer.parseInt(stridorder));
                    }
                    //订单编号
                    excelModels.add(new ExcelModel(count, 0, Utils.getObjectToString(resultmap.get("bmordernum"))));

                    //外发单号
                    excelModels.add(new ExcelModel(count, 1, Utils.getObjectToString(resultmap.get("bmioutternum"))));
                    //装钉类型
                    excelModels.add(new ExcelModel(count, 2, ConstantUtils.protypeMap.get(Utils.getObjectToInteger(resultmap.get("bmiprotype")))));
                    //名称及规格
//                    excelModels.add(new ExcelModel(count, 3, Utils.getObjectToString(resultmap.get("bmiproname")) + ","
//                            + Utils.getObjectToString(resultmap.get("bmorderitemcol"))));
                    productname =  Utils.getObjectToString(resultmap.get("bmiproname"));
                    excelModels.add(new ExcelModel(count, 3,productname,false,true,12,16));
//                    excelModels.add(new ExcelModel(count, 3,productname,true));
                    //单位
                    excelModels.add(new ExcelModel(count, 4, Utils.getObjectToString(resultmap.get("bmiunit"))));
                    //数量
                    excelModels.add(new ExcelModel(count, 5, Utils.getObjectToString(resultmap.get("bminum"))));
                    //单价
                    excelModels.add(new ExcelModel(count, 6, Utils.getObjectToString(resultmap.get("bmiprice"))));
                    //金额
                    strbmiamount = Utils.getObjectToString(resultmap.get("bmiamount"));
                    if (!StringUtils.isEmpty(strbmiamount)) {
                        bmiamount = bmiamount.add(new BigDecimal(strbmiamount));
                    }
                    excelModels.add(new ExcelModel(count, 7, strbmiamount));
                    count++;
                }
                List<Bmaddcosts> bmaddcostses = this.bmorderService.findCostsByIdbmorder(Integer.parseInt(stridorder));
                if (bmaddcostses != null && bmaddcostses.size() > 0) {
                    BigDecimal costs = new BigDecimal(0);
                    addCostsDesc = addCostsDesc + "额外费用：\r\n";
                    for (Bmaddcosts bmaddcosts : bmaddcostses) {
                        if (bmaddcosts.getBmcosts() != null) {
                            costs = costs.add(bmaddcosts.getBmcosts());
                            addCostsDesc = addCostsDesc + bmaddcosts.getBmcostsdesc() + ":" + bmaddcosts.getBmcosts().setScale(2).doubleValue() + "\r\n";
                        }
                    }
                    if (costs.intValue() != 0) {
                        bmiamount = bmiamount.add(costs);

                    }

                }

            }

        }
        //额外费用
        excelModels.add(new ExcelModel(4, 8, addCostsDesc, true));


        Bmmarker bmmarker = null;
        synchronized (this) {
            Integer bmmtype = NumUtil.getCurrDeType();
            bmmarker = this.bmmarkerService.selectByBmmtype(bmmtype);
            if (bmdenum == null) {
                if (bmmarker == null) {
                    bmmarker = new Bmmarker();
                    bmmarker.setBmmtype(bmmtype);
                    bmmarker.setBmmnum(1);
                    bmdenum = bmmtype + 1;
                } else {
                    bmdenum = bmmarker.getBmmtype() + bmmarker.getBmmnum() + 1;
                    bmmarker.setBmmnum(bmmarker.getBmmnum() + 1);
                }
                this.bmorderService.saveBmNum(emptyDeNumls, bmmarker, Boolean.FALSE);
            } else {
                bmmarker = new Bmmarker();
                bmmarker.setBmmnum(bmdenum);
                this.bmorderService.saveBmNum(emptyDeNumls, bmmarker, Boolean.TRUE);
            }


        }

        excelModels.add(new ExcelModel(15, 0, "金额(大写)：" +
                Utils.convertToChineseNumberNew(bmiamount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                + " ￥：" + bmiamount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
        String cell2 = "收货单位：";
        if (StringUtils.isEmpty(bmcusname)) {
            cell2 = cell2 + "                                                        ";
        } else {
            cell2 = cell2 + bmcusname + "                ";
        }
        if (StringUtils.isEmpty(bmdeliverydate)) {
            cell2 = cell2 + "20     年    月    日";
        } else {
            cell2 = cell2 + bmdeliverydate;
        }
        excelModels.add(new ExcelModel(2, 0, cell2));

        excelModels.add(new ExcelModel(1, 0, "                                               送货清单                                   N0:" + bmdenum));

        lists.add(excelModels);
        String num = String.valueOf(bmdenum);
        modifyAndExportExcel.modifyExcel(response, filepath, "stemplate.xls", lists, iduser, num);

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


        String[] rowsName = new String[]{"序号", "订单编号","外发编号","送货编号", "客户名称", "开单日期", "交货日期", "产品规格"
                , "产品名称", "包装要求", "产品类型", "单价","数量", "金额", "额外费用", "订单金额", "备注", "订单状态", "负责人", "额外费用明细"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        List datalist = pageInfo.getList();
        String[] addcostsarr = null;
        Object addcostsobj = null;
        String[] addcostsdescarr = null;
        Object addcostsdescobj = null;


        Map rowmap = null;
        for (int i = 0; i < datalist.size(); i++) {
            rowmap = (Map) datalist.get(i);
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = rowmap.get("bmordernum");
            objs[2] = rowmap.get("bmioutternum");
            objs[3] = rowmap.get("bmdenum");
            objs[4] = rowmap.get("bmcusname");
            objs[5] = rowmap.get("bmbillingdate");
            objs[6] = rowmap.get("bmbillingdate");
            objs[7] = rowmap.get("bmorderitemcol");
            objs[8] = rowmap.get("bmiproname");
            objs[9] = rowmap.get("bmpacreq");

            objs[10] = ConstantUtils.protypeMap.get(rowmap.get("bmiprotype"));
            objs[11] = rowmap.get("bmiprice");
            objs[12] = rowmap.get("bminum");
            objs[13] = rowmap.get("bmiamount");
            objs[14] = rowmap.get("addcosts");
            objs[15] = rowmap.get("bmorderamount");
            objs[16] = rowmap.get("bmcomments");

            objs[17] = ConstantUtils.statusMap.get(rowmap.get("bmstatus"));
            objs[18] = rowmap.get("ownername");

            addcostsobj = rowmap.get("addcostsarr");
            addcostsdescobj = rowmap.get("addcostsdescarr");
            if (addcostsobj != null && addcostsdescobj != null) {
                StringBuilder adddesc = new StringBuilder("");
                addcostsarr = String.valueOf(addcostsobj).split(",");
                addcostsdescarr = String.valueOf(addcostsdescobj).split(",");
                if (addcostsarr.length == addcostsdescarr.length) {
                    for (int j = 0; j < addcostsarr.length; j++) {
                        if (j != 0) {
                            adddesc.append(" ;");
                        }
                        adddesc.append(addcostsdescarr[j] + ":" + addcostsarr[j]);
                    }
                }
                objs[19] = adddesc.toString();
            } else {
                objs[19] = "";
            }


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
