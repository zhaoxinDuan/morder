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
                <input type="hidden" name="changebmorderamount" id="changebmorderamount" value="0">
                <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">

                    <tr>
                        <th>产品类型<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <select id="bmiprotype" name="bmiprotype" class="easyui-combobox"
                                    data-options="editable:false "
                                    style="width:200px;">
                                <option value="0">成品折页</option>
                                <option value="6">书本折页</option>
                                <option value="1">切单张</option>
                                <option value="2" selected>骑马钉</option>
                                <option value="3">锁线胶装</option>
                                <option value="7">无线胶装</option>
                                <option value="4">精装</option>
                                <option value="5">YO装</option>

                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>产品名称<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiproname" id="bmiproname" class="textInput textbox-width"
                                   style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>单位<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <%--<input type="text" name="bmiunit" id="bmiunit" class="textInput textbox-width"--%>
                                   <%--style="resize:none;width:200px;height:20px">--%>
                                <select id="bmiunit" name="bmiunit" class="easyui-combobox"
                                        data-options="editable:true "
                                        style="width:200px;">
                                    <option value="本">本</option>
                                    <option value="件">件</option>
                                </select>
                        </td>
                    </tr>
                    <tr>
                        <th>单价</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiprice" id="bmiprice"
                                   style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>数量</th>
                        <td style="text-align:left;">
                            <input type="text" name="bminum" id="bminum" style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>金额</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmiamount" id="bmiamount" readonly class="textInput textbox-width"
                                   style="resize:none;width:200px;height:20px" style="width:200px;" value="0">
                        </td>
                    </tr>
                    <tr>
                        <th>是否含税</th>
                        <td style="text-align:left;">
                            <select id="bmiistax" name="bmiistax" class="easyui-combobox"
                                    data-options="editable:false "
                                    style="width:200px;">
                                <option value="0">含税</option>
                                <option value="1" selected>不含税</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>外发编号</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmioutternum" id="bmioutternum" class="textInput textbox-width"
                                   style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>产品规格</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmorderitemcol" id="bmorderitemcol" class="textInput textbox-width"
                                   style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>
                    <tr>
                        <th>包装要求</th>
                        <td style="text-align:left;">
                            <input type="text" name="bmipacreq" id="bmipacreq" class="textInput textbox-width"
                                   style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    var oldChangeBmiamount = 0;
    $(document).ready(function () {


        $("#bmiprice").numberbox({
            precision: "2",
            max: "99999999.99",
            size: "10",
            maxlength: "10",
            onChange: function (newValue, oldValue) {
                var bminum = $("#bminum").val();
                var tmp = 0;
                if (judgeNumber(bminum)) {
                    tmp = (parseFloat(newValue) * parseFloat(bminum)).toFixed(2);
                        oldChangeBmiamount = (parseFloat(oldValue) * parseFloat(bminum)).toFixed(2);

                } else {
                    tmp = newValue;
                }
                $("#bmiamount").val(tmp);


            }
        });

        $("#bminum").numberbox({
            max: "9999999999",
            size: "10",
            maxlength: "10",
            onChange: function (newValue, oldValue) {
                var bmiprice = $("#bmiprice").val();
                var tmp = 0;
                if (judgeNumber(bmiprice)) {
                    tmp = (parseFloat(newValue) * parseFloat(bmiprice)).toFixed(2)
                    oldChangeBmiamount = (parseFloat(oldValue) * parseFloat(bmiprice)).toFixed(2);
                } else {
                    tmp = newValue;
                }
                $("#bmiamount").val(tmp);
                $("#bmiamount_tmp").val(tmp);
            }
        });

        $('#bmitemlist').datagrid({
                    <c:if test="${bmorder!=null}">
                    url: '<c:url value="/home/bm/findItemsByIdbmorder.do?_csrf=${_csrf.token}"/>&idbmorder=${bmorder.idbmorder}&t=' + new Date().getTime(),
                    </c:if>
//                    title: '字段定义列表',
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
                                    str = "成品折页";
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
                                }else if (value == 6) {
                                    str = "书本折页";
                                }else if (value == 7) {
                                    str = "无线胶装";
                                }
                                return str;
                            }
                        },
                        {
                            field: 'bmiproname',
                            title: '产品名称',
                            width: 50
                        },
                        {field: 'bmiunit', title: '单位', width: 50},
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
                        {field: 'bmorderitemcol', title: '产品规格', width: 50},
                        {field: 'bmipacreq', title: '包装要求', width: 50,algin:'left'}

                    ]],
                    toolbar: [{
                        text: '添加',
                        iconCls: 'icon-add',
                        handler: function () {

                            $('#bmitemDialog').dialog('open');

                            $("#idbmitem").val('');
                            $("#bmorderIdbmorder").val($("#idbmorder").val());
                            $("#bmiproname").val('');
                            $('#bmiprice').numberbox('setValue', 0);
                            $('#bminum').numberbox('setValue', 0);
                            $("#bmiamount").val(0);
                            $("#bmioutternum").val('');
                            $("#bmorderitemcol").val('');
                            $("#bmipacreq").val('');
//                            $("#bmiunit").val('');

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
//                                $("#bmiprice").val(record.bmiprice);
                                $('#bmiprice').numberbox('setValue', record.bmiprice);
//                                $("#bminum").val(record.bminum);
                                $('#bminum').numberbox('setValue', record.bminum);
                                $("#bmiamount").val(record.bmiamount);
                                $("#bmioutternum").val(record.bmioutternum);
                                $("#bmorderitemcol").val(record.bmorderitemcol);
                                $("#bmipacreq").val(record.bmipacreq);
//                                $("#bmiunit").val(record.bmiunit);
                                $("#bmiprotype").combobox("setValue", record.bmiprotype);
                                $("#bmiistax").combobox("setValue", record.bmiistax);
                                var _bminit = strTrimAll(record.bmiunit);
                                if(_bminit!="本"&&_bminit!="件"){
                                    $("#bmiunit").append("<option value='"+_bminit+"' selected>"+_bminit+"</option>");
                                }else{
                                    $("#bmiunit").combobox("setValue", _bminit);
                                }
                            }
                        }
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: function () {
                            var record = $('#bmitemlist').datagrid('getSelected');

                            if (record == null) {
                                $.messager.confirm('提示', '请选择某行数据再进行删除。', 'info');
                            } else {
//                                $.messager.defaults = { ok: "是", cancel: "否" };
                                $.messager.confirm("操作提示", "您确定要执行删除操作吗？", function (data) {
                                    if (data) {
                                        var bmiamount = record.bmiamount;
                                        $.ajax({
                                            type: "POST",
                                            url: '<c:url value="/home/bm/delBmorderItemById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                            dataType: "json",
                                            data: {
                                                idbmitem: record.idbmitem, bmiamount: bmiamount
                                            },
                                            beforeSend: function () {
                                                $.messager.progress({
                                                    text: '请求正在提交中，请稍候...'
                                                });
                                            },
                                            success: function (msg) {
                                                $.messager.progress('close');
                                                if (msg.success == true) {
                                                    $("#bmorderamount").val(($("#bmorderamount").val()-parseFloat(bmiamount)).toFixed(2));
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
                                    else {
                                    }
                                });

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
            height: 420,
            title: "编辑",
            closed: true,
            buttons: [{
                text: '提交',
                iconCls: 'icon-ok',
                handler: function () {
                    if (isEmpty('bmiproname', '产品名称'))return;
                    if (isEmpty('bmorderIdbmorder', '订单错误'))return;
                    if(oldChangeBmiamount=="NaN")oldChangeBmiamount = 0;
                    var changebmorderamount = (parseFloat($("#bmiamount").val()) - parseFloat(oldChangeBmiamount) + parseFloat($("#bmorderamount").val())).toFixed(2);
                    $("#changebmorderamount").val(changebmorderamount);

                    var data = $("#subform").serializeArray();
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/home/bm/saveMorderItemInfo.do?_csrf=${_csrf.token}"/>&changebmorderamount='+changebmorderamount+'&t=' + new Date().getTime(),
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
                                $("#bmorderamount").val(changebmorderamount);
                                $('#bmitemDialog').dialog('close');


                            } else {
                                $.messager.alert('操作失败', '保存失败！', 'error');
                            }
                        },
                        error: function (msg) {
                            $.messager.progress('close');
                            $.messager.alert('操作失败', '更新失败！'+msg, 'error');
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
