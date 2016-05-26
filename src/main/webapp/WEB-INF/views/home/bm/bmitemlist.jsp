<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="bmitemlistDiv">
    <div class="eleContainer titleText textS16">
        产品列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="bmitemlist"></table>
    </div>
    <div id="bmitemDialog">
        <div class="eleContainer elePaddingBtm">
            <form id="subform">
                <input type="hidden" name="bmorderIdbmorder" id="bmorderIdbmorder">
                <input type="hidden" name="idbmitem" id="idbmitem">
                <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">

                    <tr>
                        <th>产品类型<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <select id="bmiprotype" name="bmiprotype" class="easyui-combobox"
                                    data-options="editable:false "
                                    style="width:200px;">

                                <option value="0">锁线</option>
                                <option value="1">无线</option>
                                <option value="2">成品折页</option>
                                <option value="3">书本折页</option>
                                <option value="4" selected>骑订</option>
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
                            <input type="text" name="bmiprice" id="bmiprice" style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>数量</th>
                        <td style="text-align:left;">
                            <input type="text" name="bminum" id="bminum" style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>金额</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiamount" id="bmiamount" readonly class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>是否含税</th>
                        <td style="text-align:left;">
                            <select id="bmiistax" name="bmiistax" class="easyui-combobox"
                                    data-options="editable:false "
                                    style="width:200px;">
                                <option value="0" selected>含税</option>
                                <option value="1">不含税</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>外发编号</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmioutternum" id="bmioutternum" class="textInput textbox-width"
                                   style="resize:none;width:96%;height:20px">
                        </td>
                    <tr>
                        <th>产品规格</th>
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

    $(document).ready(function () {


        $("#bmiprice").numberbox({
            precision: "2",
            max: "99999999.99",
            size: "10",
            maxlength: "10",
            onChange: function (newValue, oldValue) {
                var bminum = $("#bminum").val();
                if (judgeNumber(bminum)) {
                    $("#bmiamount").val((parseFloat(newValue) * parseFloat(bminum)).toFixed(2));
                } else if (!judgeNumber(bminum)) {
                    $("#bmiamount").val(newValue);
                }
            }
        });

        $("#bminum").numberbox({
            max: "9999999999",
            size: "10",
            maxlength: "10",
            onChange: function (newValue, oldValue) {
                var bmiprice = $("#bmiprice").val();
                if (judgeNumber(bmiprice)) {
                    $("#bmiamount").val((parseFloat(newValue) * parseFloat(bmiprice)).toFixed(2));
                } else if (!judgeNumber(bmiamount_tmp)) {
                    $("#bmiamount").val(newValue);
                }
            }
        });

        $('#bmitemlist').datagrid({
                    <c:if test="${bmorder!=null}">
                    url: '<c:url value="/home/bm/findItemsByIdbmorder.do?_csrf=${_csrf.token}"/>&idbmorder=${bmorder.idbmorder}&t=' + new Date().getTime(),
                    </c:if>
                    title: '字段定义列表',
                    pagination: true,
                    fitColumns: true,
                    singleSelect: true,
                    pageSize: 15,
                    pageList: [15, 30, 45],
                    collapsible: true,
                    rownumbers: true,
                    columns: [[
                        {
                            field: 'bmiprotype', title: '产品类型', width: 50,
                            formatter: function (value, row, index) {
                                var str = "";
                                if (value == 0) {
                                    str = "锁线";
                                } else if (value == 1) {
                                    str = "无线";
                                } else if (value == 2) {
                                    str = "成品折页";
                                } else if (value == 3) {
                                    str = "书本折页";
                                } else if (value == 4) {
                                    str = "骑订";
                                }
                                return str;
                            }
                        },
                        {
                            field: 'bmiproname',
                            title: '产品名称',
                            width: 50
                        },
                        {field: 'bmiprice', title: '单价', width: 50},
                        {field: 'bminum', title: '数量', width: 50},
                        {field: 'bmiamount', title: '金额', width: 50},
                        {
                            field: 'bmiistax', title: '是否含税', width: 50,
                            formatter: function (value, row, index) {
                                if (value == 0) {
                                    return "含税";
                                } else {
                                    return "不含税";
                                }
                            }
                        },
                        {field: 'bmioutternum', title: '外发编号', width: 50},
                        {field: 'bmorderitemcol', title: '产品规格', width: 50}

                    ]],
                    toolbar: [{
                        text: '添加',
                        iconCls: 'icon-add',
                        handler: function () {

                            $('#bmitemDialog').dialog('open');

                            $("#idbmitem").val('');
                            $("#bmorderIdbmorder").val($("#idbmorder").val());
                            $("#bmiproname").val('');
//                            $("#bmiprice").val('');
                            $('#bmiprice').numberbox('setValue', 0);
                            $('#bminum').numberbox('setValue', 1);
//                            $("#bminum").val('');
                            $("#bmiamount").val('');
                            $("#bmioutternum").val('');
                            $("#bmorderitemcol").val('');

                        }
                    }, '-', {
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var record = $('#bmitemlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行编辑。', 'info');
                            } else {
                                $('#bmitemDialog').dialog('open');
                                $("#bmorderIdbmorder").val($("#idbmorder").val());

                                $("#idbmitem").val(record.idbmitem);
                                $("#bmiproname").val(record.bmiproname);
                                $("#bmiprice").val(record.bmiprice);
                                $("#bminum").val(record.bminum);
                                $("#bmiamount").val(record.bmiamount);
                                $("#bmioutternum").val(record.bmioutternum);
                                $("#bmorderitemcol").val(record.bmorderitemcol);
                                $("#bmiprotype").combobox("setValue", record.bmiprotype);
                                $("#bmiistax").combobox("setValue", record.bmiistax);
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
                                    url: '<c:url value="/home/bm/delBmorderItemById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                    dataType: "json",
                                    data: {
                                        idbmitem: record.idbmitem,
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
            height: 380,
            title: "用户定义",
            closed: true,
            buttons: [{
                text: '提交',
                iconCls: 'icon-ok',
                handler: function () {
                    if (isEmpty('bmiproname', '产品名称'))return;
                    if (isEmpty('bmorderIdbmorder', '订单错误'))return;

                    var data = $("#subform").serializeArray();
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/home/bm/saveMorderItemInfo.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
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
                                $("#bmorderamount").val((parseFloat($("#bmiamount").val()) + parseFloat($("#bmorderamount").val())).toFixed(2));
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

    })

</script>
