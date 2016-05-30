<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="fieldDefDiv">
    <div class="eleContainer titleText textS16">
        客户列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="cuslist"></table>
    </div>
    <div id="cusDefDialog">
        <div class="eleContainer elePaddingBtm">
            <form id="subform">
                <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">
                    <input type="hidden" id="idcustomer" name="idcustomer">
                    <input type="hidden" id="tcustomerappendIdcusapp" name="tcustomerappendIdcusapp">
                    <input type="hidden" id="idcusapp" name="tcustomerappend.idcusapp">
                    <input type="hidden" id="tcustomertaxIdtcustax" name="tcustomertaxIdtcustax">
                    <input type="hidden" id="idtcustax" name="tcustomertax.idtcustax">
                    <input type="hidden" id="cofficername" name="cofficername">

                    <tr>
                        <th>客户名称<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <input type="text" name="cname" id="cname" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>客户地址</th>
                        <td style="text-align:left;">
                            <input type="text" name="caddress" id="caddress" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>客户归属</th>
                        <td style="text-align:left;">
                            <select id="csettype" name="csettype" class="easyui-combobox"
                                    data-options="editable:false "
                                    style="width:200px;">
                                <option value="0" selected>公司共享</option>
                                <option value="1">个人所有</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>负责人</th>
                        <td style="text-align:left;">
                            <input id="cofficeruid" name="cofficeruid" class="easyui-combobox"
                                   data-options="editable:false "
                                   style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td style="text-align:left;">
                            <input type="text" name="comments" id="comments" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>

                    <tr>
                        <th>联系人</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomerappend.cacontacts" id="cacontacts"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>电话</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomerappend.catelphone" id="catelphone"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>手机</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomerappend.camphone" id="camphone"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>传真</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomerappend.cafax" id="cafax"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>收货地址</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomerappend.carecaddress" id="carecaddress"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <th colspan="2">开票信息</th>
                    <tr>
                        <th>单位名称</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomertax.tctcompname" id="tctcompname"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>开票地址</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomertax.tcbillingaddr" id="tcbillingaddr"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>税号</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomertax.tcbillingnum" id="tcbillingnum"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>开户银行</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomertax.tcbankname" id="tcbankname"
                                   class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>账号</th>
                        <td style="text-align:left;">
                            <input type="text" name="tcustomertax.tcbankaccount" id="tcbankaccount"
                                   class="textInput textbox-width"
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
        $('#cusDefDialog').dialog('open');
        $("#idcustomer").val(record.idcustomer);
        $("#tcustomerappendIdcusapp").val(record.tcustomerappendIdcusapp);
        $("#idcusapp").val(record.tcustomerappendIdcusapp);
        $("#tcustomertaxIdtcustax").val(record.tcustomertaxIdtcustax);
        $("#idtcustax").val(record.tcustomertaxIdtcustax);
        $("#csettype").combobox("setValue", record.csettype);
        $("#cofficeruid").combobox("setValue", record.cofficeruid);
        $("#cname").val(record.cname);
        $("#caddress").val(record.caddress);
        $("#comments").val(record.comments);

        $("#tctcompname").val(record.tcustomertax.tctcompname);
        $("#tcbillingaddr").val(record.tcustomertax.tcbillingaddr);
        $("#tcbillingnum").val(record.tcustomertax.tcbillingnum);
        $("#tcbankname").val(record.tcustomertax.tcbankname);
        $("#tcbankaccount").val(record.tcustomertax.tcbankaccount);

        $("#cacontacts").val(record.tcustomerappend.cacontacts);
        $("#catelphone").val(record.tcustomerappend.catelphone);
        $("#camphone").val(record.tcustomerappend.camphone);
        $("#cafax").val(record.tcustomerappend.cafax);
        $("#carecaddress").val(record.tcustomerappend.carecaddress);
    }
    function readOnlyDetails(record){
        openDetailAndFillValues(record);
        $('input').attr("readonly",true);
        $(".dialog-button").hide();

    }
    $(document).ready(function () {
        var url = '<c:url value="/home/cus/findAllCustomers.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime();
        $('#cuslist').datagrid({
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
                            $('#cusDefDialog').dialog('open');
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
                            var record = $('#cuslist').datagrid('getSelected');
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
                            var record = $('#cuslist').datagrid('getSelected');
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
                                            $('#cuslist').datagrid('reload');
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
                    },'-', {
                        text: '查看',
                        iconCls: 'icon-search',
                        handler: function () {
                            var record = $('#cuslist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行查看。', 'info');
                            } else {
                                readOnlyDetails(record);
                            }
                        }
                    }],
                    onLoadSuccess: function () {
                        $('#cuslist').datagrid('resize');
                    },
                    onDblClickRow: function (index, row) {

                    }
                }
        );

        $('#cusDefDialog').dialog({
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
                    if((uid==null||uid=="")||(uname==null||uname=="")){
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
                                $('#cuslist').datagrid('reload');
                                $('#cusDefDialog').dialog('close');
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
                        $('#cusDefDialog').dialog('close');
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
