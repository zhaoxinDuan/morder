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
                    <th>开单日期 从</th>
                    <td style="text-align:left;">
                        <input id="searchBmbillingdateFrom" name="searchBmbillingdateFrom" type="text"
                               class="easyui-datebox"
                               style="width:200px;">

                    </td>
                    <th>至 开单日期</th>
                    <td style="text-align:left;">
                        <input type="text" name="searchBmbillingdateTo" id="searchBmbillingdateTo"
                               class="easyui-datebox"
                               style="width:200px;">
                    </td>
                </tr>
                <tr>
                    <th>交货日期 从</th>
                    <td style="text-align:left;">
                        <input id="searchBmdeliverydateFrom" name="searchBmdeliverydateFrom" type="text"
                               class="easyui-datebox"
                               style="width:200px;">

                    </td>
                    <th>至 交货日期</th>
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
        跟单列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="bmlist"></table>
    </div>
</div>


<script type="text/javascript">
    function checkSHCheckBox(idbmitem, bmstatus) {
        if (bmstatus == 2) {
//            $.messager.alert('提示', '已完成订单不能再生成送货清单。', 'info');
//            $("#"+idbmitem).attr("checked",false);
        }

    }

    $(document).ready(function () {

        $('#bmlist').datagrid({
                    url: '<c:url value="/home/bm/findAllBmorders.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
//                    title: '处理中订单列表',
                    pagination: true,
//                    fitColumns: true,
                    singleSelect: true,
                    pageSize: 10,
                    pageList: [10, 20, 30],
                    collapsible: true,
                    rownumbers: true,
                    frozenColumns: [[
                        {
                            field: 'op11', title: '送货清单', width: 50, algin: "center",
                            formatter: function (value, row, index) {
                                return '<input type="checkbox" id="' + row.idbmorder + '" onclick="checkSHCheckBox(' + row.idbmorder + ',' + row.bmstatus + ')"  ' +
                                        'name="op11"  value="' + row.idbmorder + '">';
                            }
                        },
                        {
                            field: 'bmordernum', title: '订单编号', width: 110,
                            formatter: function (value, row, index) {
                                var url = '<c:url value="/home/bm/bmindex.do?_csrf=${_csrf.token}"/>&isedit=true&idbmorder=' + row.idbmorder + '&t=' + new Date().getTime();
                                return '<a href="' + url + '">' + value + '</a>';
                            }
                        },
                        {field: 'bmcusname', title: '客户名称', width: 90}

                    ]],
                    columns: [[

                        {field: 'product', title: '产品', width: 200},
                        {
                            field: 'protype', title: '产品类型', width: 200,
                            formatter: function (value, row, index) {
                                var str = "";
                                if (value != null) {
                                    var strs = value.split(",");
                                    for (var i = 0; i < strs.length; i++) {
                                        if (i != 0)str = str + ",";
                                        if (strs[i] == 0) {
                                            str = str + "成品折页";
                                        } else if (strs[i] == 1) {
                                            str = str + "切单张";
                                        } else if (strs[i] == 2) {
                                            str = str + "骑马钉";
                                        } else if (strs[i] == 3) {
                                            str = str + "锁线胶装";
                                        } else if (strs[i] == 4) {
                                            str = str + "精装";
                                        } else if (strs[i] == 5) {
                                            str = str + "YO装";
                                        } else if (strs[i] == 6) {
                                            str = str + "书本折页";
                                        } else if (strs[i] == 7) {
                                            str = str + "无线胶装";
                                        }

                                    }

                                    return str;
                                }
                            }
                        },
                        {field: 'bmorderamount', title: '订单金额', width: 80},
                        {
                            field: 'bmbillingdate', title: '开单日期', width: 80,
                            formatter: function (value, row, index) {

                                return formatDataFromNumber(value);
                            }
                        },
                        {
                            field: 'bmdeliverydate', title: '交货日期', width: 80,
                            formatter: function (value, row, index) {
                                return formatDataFromNumber(value);
                            }
                        },
                        {
                            field: 'bmstatus', title: '订单状态', width: 80,
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
                        {field: 'ownername', title: '负责人', width: 80},
                        {field: 'bmdenum', title: '送货编号', width: 80},
                        {
                            field: 'operation', title: '操作', width: 80, align: 'left',
                            formatter: function (value, row) {
                                var idbmorder = row.idbmorder;
                                var str = "";
                                if (row.bmstatus == 1) {
                                    str = '<a href="#"  onclick="updateMorder(' + idbmorder + ',2)" style="font-size: 12px;">完成订单</a>';
                                } else if (row.bmstatus == 0) {
                                    str = '<a href="#"  onclick="updateMorder(' + idbmorder + ',1)" style="font-size: 12px;">提交订单</a>';
                                }
                                return str;

                            }
                        }
                    ]],
                    toolbar: [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var record = $('#bmlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行编辑。', 'info');
                            } else {
                                window.location.href = '<c:url value="/home/bm/bmindex.do?_csrf=${_csrf.token}"/>&isedit=true&idbmorder=' + record.idbmorder + '&t=' + new Date().getTime();
                            }
                        }
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: function () {
                            var record = $('#bmlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行删除。', 'info');
                            } else {
                                if (record.bmstatus == 2) {
                                    $.messager.alert('提示', '已完成订单不能进行删除！', 'info');
                                } else {
                                    $.messager.confirm("操作提示", "您确定要执行删除操作吗？", function (data) {
                                        if (data) {
                                            $.ajax({
                                                type: "POST",
                                                url: '<c:url value="/home/bm/delBmorderById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                                dataType: "json",
                                                data: {
                                                    idbmorder: record.idbmorder,
                                                },
                                                beforeSend: function () {
                                                    $.messager.progress({
                                                        text: '请求正在提交中，请稍候...'
                                                    });
                                                },
                                                success: function (msg) {
                                                    $.messager.progress('close');
                                                    if (msg.success == true) {
                                                        $.messager.alert('操作成功', '删除成功。', 'info');
                                                        $('#bmlist').datagrid('reload');
                                                    } else {
                                                        $.messager.alert('操作失败', '删除失败！', 'error');
                                                    }
                                                },
                                                error: function (msg) {
                                                    $.messager.progress('close');
                                                    $.messager.alert('操作失败', '后台出现异常！' + msg, 'error');
                                                }
                                            })
                                        }
                                    })
                                }
                            }
                        }
                    }, '-', {
                        text: '复制新建',
                        iconCls: 'icon-cut',
                        handler: function () {
                            var record = $('#bmlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行复制新建。', 'info');
                            } else {

                                $.ajax({
                                    type: "POST",
                                    url: '<c:url value="/home/bm/copyOrder.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                    dataType: "json",
                                    data: {
                                        idbmorder: record.idbmorder,
                                    },
                                    beforeSend: function () {
                                        $.messager.progress({
                                            text: '请求正在提交中，请稍候...'
                                        });
                                    },
                                    success: function (msg) {
                                        $.messager.progress('close');
                                        if (msg) {
                                            window.location.href = '<c:url value="/home/bm/bmindex.do?_csrf=${_csrf.token}"/>&isedit=true&idbmorder=' + msg + '&t=' + new Date().getTime();
                                        } else {
                                            $.messager.alert('操作失败', '新建并复制失败！', 'error');
                                        }
                                    },
                                    error: function (msg) {
                                        $.messager.progress('close');
                                        $.messager.alert('操作失败', '后台出现异常！' + msg, 'error');
                                    }
                                })

                            }
                        }
                    }, '-----', {
                        text: '生成工程单',
                        iconCls: 'icon-print',
                        handler: function () {
                            var record = $('#bmlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择需要生成工程单的数据。', 'info');
                            } else {
                                openPostWindow('<c:url value="/home/execl/createptemplate.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                        "生成工程单", {
                                            idbmorder: record.idbmorder, iduser:${iduser}
                                        });

                            }
                        }
                    }, '-',
                        {
                            text: '生产送货清单',
                            iconCls: 'icon-print',
                            handler: function () {
                                var items = $("input[name='op11']:checked");
                                var result = "";
                                $.each(items, function (index, item) {

                                    result = result + "|" + item.value;

                                });
                                if (result == "") {
                                    $.messager.alert('提示', '请选择勾选需要生产的送货清单数据。', 'info');
                                } else {
                                    openPostWindow('<c:url value="/home/execl/createstemplate.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                            "生产送货清单", {
                                                idbmorders: result, iduser:${iduser}
                                            });

                                }
                            }
                        }
                    ],
                    onLoadSuccess: function (data) {
                        $('#bmlist').datagrid('resize');
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
            $.messager.progress({
                text: '请求正在提交中，请稍候...'
            });
            $('#bmlist').datagrid('load', {
                searchBmstatus: $("#searchBmstatus").combobox("getValue"),
                searchTcustomerIdcustomer: $("#searchTcustomerIdcustomer").combobox("getValue"),
                searchBmbillingdateFrom: $("#searchBmbillingdateFrom").datebox("getValue"),
                searchBmbillingdateTo: $("#searchBmbillingdateTo").datebox("getValue"),
                searchBmdeliverydateFrom: $("#searchBmdeliverydateFrom").datebox("getValue"),
                searchBmdeliverydateTo: $("#searchBmdeliverydateTo").datebox("getValue"),
                searchTuserIduser: $("#searchTuserIduser").combobox("getValue")
            });
            $.messager.progress('close');

        });
        $("#search_bmorder_reset").bind("click", function () {
            $('#search_morderform').form('reset');
        });

    })

    function updateMorder(idbmorder, bmstatus) {
        $.messager.confirm("操作提示", "您确定要操作该订单吗？", function (data) {
            if (data) {
                $.ajax({
                    type: "POST",
                    url: '<c:url value="/home/bm/updateMorder.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                    dataType: "json",
                    data: {
                        idbmorder: idbmorder, bmstatus: bmstatus
                    },
                    beforeSend: function () {
                        $.messager.progress({
                            text: '请求正在提交中，请稍候...'
                        });
                    },
                    success: function (msg) {
                        $.messager.progress('close');
                        if (msg.success == true) {
                            $.messager.alert('操作成功', '操作成功。', 'info');
                            $('#bmlist').datagrid('reload');
                        } else {
                            $.messager.alert('操作失败', '操作失败！', 'error');
                        }
                    },
                    error: function (msg) {
                        $.messager.progress('close');
                        $.messager.alert('操作失败', '后台出现异常！' + msg, 'error');
                    }
                })
            }
        })
    }

</script>
<jsp:include page="./../../footer.jsp"></jsp:include>
</body>
</html>
