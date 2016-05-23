<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="fieldDefDiv">
    <div class="eleContainer titleText textS16">
        客户列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="bmitemlist"></table>
    </div>
    <div id="bmitemDialog">
        <div class="eleContainer elePaddingBtm">
            <form id="subform">
                <input type="hidden" name="bmorderIdbmorder" id="bmorderIdbmorder">
                <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">

                    <tr>
                        <th>产品类型<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <select id="bmiprotype" name="bmiprotype" class="easyui-combobox"
                                    data-options="editable:false "
                                    style="width:200px;">
                                <option>--------</option>
                                <option value="0">锁线</option>
                                <option value="1">无线</option>
                                <option value="1">成品折页</option>
                                <option value="1">无线</option>
                                <option value="1">无线</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>产品名称</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiproname" id="bmiproname" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>单价</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiprice" id="bmiprice" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>数量</th>
                        <td style="text-align:left;">
                            <input type="text" name="bminum" id="bminum" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>金额</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiamount" id="bmiamount" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>是否含税</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiistax" id="bmiistax" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>bmioutternum</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmioutternum" id="bmioutternum" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    <tr>
                        <th>bmorderitemcol</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmorderitemcol" id="bmorderitemcol" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    function openDetailAndFillValues(record) {
        $('#bmitemDialog').dialog('open');

    }

    $(document).ready(function () {
        var url = '<c:url value="/home/cus/findAllCustomers.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime();
        $('#bmitemlist').datagrid({
                    url: url,
                    title: '字段定义列表',
                    pagination: true,
                    fitColumns: true,
                    singleSelect: true,
                    pageSize: 15,
                    pageList: [15, 30, 45],
                    collapsible: true,
                    rownumbers: true,
                    loadFilter: pagerFilter,
                    columns: [[
                        {
                            field: 'cname', title: '客户名称', width: 50
                        },
                        {
                            field: 'tcustomerappendIdcusapp',
                            title: '联系人',
                            width: 50,
                            formatter: function (value, row, index) {
                                if (row.tcustomerappend && row.tcustomerappend) {
                                    return row.tcustomerappend.cacontacts + "    " + row.tcustomerappend.camphone;
                                }

                            }
                        },
                        {field: 'caddress', title: '客户地址', width: 50},
                        {
                            field: 'csettype', title: '客户类型', width: 50,
                            formatter: function (value, row, index) {
                                if (value == 1) {
                                    return "个人客户";
                                } else {
                                    return "公司客户";
                                }
                            }
                        },
                        {field: 'cofficername', title: '负责人', width: 50},
                        {field: 'comments', title: '备注', width: 50}

                    ]],
                    toolbar: [{
                        text: '添加',
                        iconCls: 'icon-add',
                        handler: function () {
                            $(".dialog-button").show();
                            $('input').attr("readonly",false);
                            $('#bmitemDialog').dialog('open');
                            $("#idcustomer").val('');
                            $("#tcustomerappendIdcusapp").val('');
                            $("#idcusapp").val('');
                            $("#tcustomertaxIdtcustax").val('');
                            $("#idtcustax").val('');
                            $("#cname").val('');
                            $("#caddress").val('');
                            $("#comments").val('');

                            $("#tctcompname").val('');
                            $("#tcbillingaddr").val('');
                            $("#tcbillingnum").val('');
                            $("#tcbankname").val('');
                            $("#tcbankaccount").val('');

                            $("#cacontacts").val('');
                            $("#catelphone").val('');
                            $("#camphone").val('');
                            $("#cafax").val('');
                            $("#carecaddress").val('');
                        }
                    }, '-', {
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            $(".dialog-button").show();
                            $('input').attr("readonly",false);
                            var record = $('#bmitemlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行编辑。', 'info');
                            } else {
                                openDetailAndFillValues(record);
                            }
                        }
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: function () {
                            var record = $('#bmitemlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行删除。', 'info');
                            } else {
                                $.ajax({
                                    type: "POST",
                                    url: '<c:url value="/home/cus/delCusById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                    dataType: "json",
                                    data: {
                                        idcustomer: record.idcustomer,
                                        idcusapp: record.tcustomerappendIdcusapp,
                                        idtcustax: record.tcustomertaxIdtcustax
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
                                            $('#bmitemlist').datagrid('reload');
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
                        }
                    }],
                    onLoadSuccess: function () {
                        $('#bmitemlist').datagrid('resize');
                    },
                    onDblClickRow: function (index, row) {

                    }
                }
        );

        $('#bmitemDialog').dialog({
            collapsible: true,
            minimizable: true,
            maximizable: true,
            cache: false,
            width: 600,
            height: 550,
            title: "用户定义",
            closed: true,
            buttons: [{
                text: '提交',
                iconCls: 'icon-ok',
                handler: function () {
                    if (isEmpty('cname', '客户名称'))return;
                    var uid =trimEasyUIValue("cofficeruid","easyui-combobox");
                    var uname =$("#cofficername").val();
                    if((uid==null||nid=="")||(uname==null||uname=="")){
                        $.messager.alert('请选择负责人', '请从下拉框重选择负责人，不要自己输入！', 'info');
                    }
                    var data = $("#subform").serializeArray();
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/home/cus/saveCusInfo.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                        dataType: "json",
                        data: data,
                        beforeSend: function () {
                            $.messager.progress({
                                text: '请求正在提交中，请稍候...'
                            });
                        },
                        success: function (msg) {
                            $.messager.progress('close');
                            if (msg.success == true) {
                                $.messager.alert('操作成功', '保存成功', 'info');
                                $('#bmitemlist').datagrid('reload');
                                $('#bmitemDialog').dialog('close');
                            } else {
                                $.messager.alert('操作失败', '保存失败！', 'error');
                            }
                        },
                        error: function (msg) {
                            $.messager.progress('close');
                            $.messager.alert('操作失败', '删除失败！', 'error');
                        }
                    })
                }
            },
                {
                    text: '关闭',
                    handler: function () {
                        $('#bmitemDialog').dialog('close');
                    }
                }
            ]
        });

        $('#cofficeruid').combobox({
            panelHeight: 'auto',
            editable: true,
            required: "true",
            valueField: 'iduser',
            textField: 'uname',
            url: '<c:url value="/home/findAllUsersNoLimit.do?_csrf=${_csrf.token}"/>',
            filter: function (q, row) {
                var opts = $(this).combobox('options');
                return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配
            },
            onLoadSuccess: function (msg) {
                $('#cofficeruid').combobox("setValue", "${tUser.iduser}");
                $("#cofficername").val("${tUser.uname}");

            },
            onChange: function (newValue, oldValue) {
                $("#cofficername").val($('#cofficeruid').combobox('getText'));
            }
        });
    })

</script>
