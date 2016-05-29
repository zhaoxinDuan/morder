<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="./../../header.jsp"></jsp:include>

</head>
<body>

<div class="ctrlContainer">

    <div class="eleContainer elePaddingBtm">
        <form id="search_morderform">
            <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">

                <tr>
                    <th>订单状态</th>
                    <td style="text-align:left;">
                        <select id="searchBmstatus" name="searchBmstatus" class="easyui-combobox"
                                data-options="editable:false "
                                style="width:200px;">
                            <option value="-1" selected>全部</option>
                            <option value="0">未提交订单</option>
                            <option value="1">已提交订单</option>
                            <option value="2">已完成订单</option>
                        </select>
                    </td>
                    <th>客户名称</th>
                    <td style="text-align:left;">
                        <input id="searchTcustomerIdcustomer" name="searchTcustomerIdcustomer" class="easyui-combobox"
                               data-options="editable:false "
                               style="width:200px;"/>
                    </td>

                </tr>
                <tr>
                    <th>from 开单日期</th>
                    <td style="text-align:left;">
                        <input id="searchBmbillingdateFrom" name="searchBmbillingdateFrom" type="text"
                               class="easyui-datebox"
                               style="width:200px;">

                    </td>
                    <th>to 开单日期</th>
                    <td style="text-align:left;">
                        <input type="text" name="searchBmbillingdateTo" id="searchBmbillingdateTo"
                               class="easyui-datebox"
                               style="width:200px;">
                    </td>
                </tr>
                <tr>
                    <th>from 交货日期</th>
                    <td style="text-align:left;">
                        <input id="searchBmdeliverydateFrom" name="searchBmdeliverydateFrom" type="text"
                               class="easyui-datebox"
                               style="width:200px;">

                    </td>
                    <th>to 交货日期</th>
                    <td style="text-align:left;">
                        <input type="text" name="searchBmdeliverydateTo" id="searchBmdeliverydateTo"
                               class="easyui-datebox"
                               style="width:200px;">
                    </td>
                </tr>
                <tr>
                    <th>负责人</th>
                    <td style="text-align:left;" colspan="3">
                        <input id="searchTuserIduser" name="searchTuserIduser" class="easyui-combobox"
                               data-options="editable:false "
                               style="width:200px;"/>
                    </td>
                </tr>

                <tr>
                    <td style="text-align: center;" colspan="4">
                        <a data-options="iconCls:'icon-search'" href="javascript:void(0)" class="easyui-linkbutton"
                           id="search_bmorder">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a data-options="iconCls:'icon-clear'" href="javascript:void(0)" class="easyui-linkbutton"
                           id="search_bmorder_reset">清空条件</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <div class="eleContainer titleText textS16">
        订单明细
        &nbsp;&nbsp;&nbsp;&nbsp;
        订单数量：<span id="totalorders"></span>
        &nbsp;&nbsp;&nbsp;&nbsp;
        产品数量：<span id="totalitems"></span>
        &nbsp;&nbsp;&nbsp;&nbsp;
        订单金额：<span id="totalamount"></span>
        <span style="float: right"><a data-options="iconCls:'icon-redo'" href="javascript:void(0)" class="easyui-linkbutton"
                 id="export_bmorder">导出execl</a></span>

    </div>

    <div class="eleContainer elePaddingBtm">
        <table id="bmlistdetail"></table>
    </div>
</div>


<script type="text/javascript">

    $(document).ready(function () {

        $('#bmlistdetail').datagrid({
                    url: '<c:url value="/home/bm/findAllBmordersByDetails.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                    title: '订单明细',
                    pagination: true,
//                    fitColumns: true,
                    singleSelect: true,
                    pageSize: 15,
                    pageList: [15, 30, 45],
//                    collapsible: true,
                    rownumbers: true,
//                    view:groupview,
//                    groupField:'idbmorder',
//                    groupFormatter:function(value,rows){
//                        return rows[0].bmordernum + ' - ' + rows.length + ' 项';
//                    },
                    frozenColumns: [[
                        {field: 'bmordernum', title: '订单编号', width: 90},
                        {field: 'bmcusname', title: '客户名称', width: 90}
                    ]],
                    columns: [[

                        {
                            field: 'bmbillingdate', title: '开单日期', width: 90,
                            formatter: function (value, row, index) {

                                return formatDataFromNumber(value);
                            }
                        },
                        {
                            field: 'bmbillingdate', title: '交货日期', width: 90,
                            formatter: function (value, row, index) {
                                return formatDataFromNumber(value);
                            }
                        },
                        {field: 'bmorderitemcol', title: '产品规格', width: 90},
                        {field: 'bmiproname', title: '产品名称', width: 90},
                        {field: 'bmpacreq', title: '包装要求', width: 90},
                        {
                            field: 'bmiprotype', title: '产品类型', width: 90,
                            formatter: function (value, row, index) {
                                var str = "";
                                if (value == 0) {
                                    str = "折页";
                                } else if (value == 1) {
                                    str = "切单张";
                                } else if (value == 2) {
                                    str = "骑马钉";
                                } else if (value == 3) {
                                    str = "锁线胶装";
                                } else if (value == 4) {
                                    str = "精装";
                                }else if (value == 5) {
                                    str = "YO装";
                                }
                                return str;
                            }
                        },
                        {field: 'bminum', title: '数量', width: 90},
                        {field: 'bmiamount', title: '金额', width: 90},
                        {field: 'bmaddcosts', title: '额外费用', width: 90},
                        {field: 'bmorderamount', title: '订单金额', width: 90},
                        {field: 'bmcomments', title: '备注', width: 90},

                        {
                            field: 'bmstatus', title: '订单状态', width: 90,
                            formatter: function (value, row, index) {
                                var str = "";
                                if (value == 0) {
                                    str = "未提交订单";
                                } else if (value == 1) {
                                    str = "已提交订单";
                                } else if (value == 2) {
                                    str = "已完成订单";
                                }
                                return str;
                            }
                        },
                        {field: 'ownername', title: '负责人', width: 90}
                    ]],
                    onLoadSuccess: function (data) {
                        $("#totalamount").html(data.othermap.totalamount);
                        $("#totalitems").html(data.othermap.totalitems);
                        $("#totalorders").html(data.othermap.totalorders);
                        $('#bmlistdetail').datagrid('resize');
                        $(this).datagrid("autoMergeCells", ['bmordernum', 'bmcusname', 'bmorderamount', 'bmbillingdate', 'bmbillingdate', 'bmorderamount', 'bmcomments']);


                    },
                    onDblClickRow: function (index, row) {

                    }
                }
        );


        $('#searchTcustomerIdcustomer').combobox({
            panelHeight: 'auto',
            editable: true,
            required: "true",
            valueField: 'idcustomer',
            textField: 'cname',
            url: '<c:url value="/home/cus/findAllCustomersNolimit.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
            filter: function (q, row) {
                var opts = $(this).combobox('options');
                return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配
            },
            onLoadSuccess: function (msg) {

            },
            onChange: function (newValue, oldValue) {
            }
        });
        $('#searchTuserIduser').combobox({
            panelHeight: 'auto',
            editable: true,
            required: "true",
            valueField: 'iduser',
            textField: 'uname',
            url: '<c:url value="/home/findAllUsersNoLimit.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
            filter: function (q, row) {
                var opts = $(this).combobox('options');
                return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配
            },
            onLoadSuccess: function (msg) {
                $('#tuserIduser').combobox("setValue", "${iduser}");

            },
            onChange: function (newValue, oldValue) {

            }
        });
        $("#search_bmorder").bind("click", function () {

            $('#bmlistdetail').datagrid('load',{
                searchBmstatus:$("#searchBmstatus").combobox("getValue"),
                searchTcustomerIdcustomer:$("#searchTcustomerIdcustomer").combobox("getValue"),
                searchBmbillingdateFrom:$("#searchBmbillingdateFrom").datebox("getValue"),
                searchBmbillingdateTo:$("#searchBmbillingdateTo").datebox("getValue"),
                searchBmdeliverydateFrom:$("#searchBmdeliverydateFrom").datebox("getValue"),
                searchBmdeliverydateTo:$("#searchBmdeliverydateTo").datebox("getValue"),
                searchTuserIduser:$("#searchTuserIduser").combobox("getValue")
            });

        });
        $("#search_bmorder_reset").bind("click", function () {
            $('#search_morderform').form('reset');
        });

        $("#export_bmorder").bind("click", function () {
            var data = $("#search_morderform").serializeArray();
            openPostWindow('<c:url value="/home/bm/exportBmlistDetail.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),"导出订单列表",{
                searchBmstatus:$("#searchBmstatus").combobox("getValue"),
                searchTcustomerIdcustomer:$("#searchTcustomerIdcustomer").combobox("getValue"),
                searchBmbillingdateFrom:$("#searchBmbillingdateFrom").datebox("getValue"),
                searchBmbillingdateTo:$("#searchBmbillingdateTo").datebox("getValue"),
                searchBmdeliverydateFrom:$("#searchBmdeliverydateFrom").datebox("getValue"),
                searchBmdeliverydateTo:$("#searchBmdeliverydateTo").datebox("getValue"),
                searchTuserIduser:$("#searchTuserIduser").combobox("getValue")
            });

        });



    })

</script>
<jsp:include page="./../../footer.jsp"></jsp:include>
</body>
</html>
