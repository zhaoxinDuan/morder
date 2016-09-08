<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="bmconstsDiv">
    <div class="eleContainer titleText textS16">
        额外费用列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="bmcostlist"></table>
    </div>
    <div id="bmcostDialog">
        <div class="eleContainer elePaddingBtm">
            <form id="costSubform">
                <input type="hidden" name="bmorderIdbmorder" id="cost_bmorderIdbmorder">
                <input type="hidden" name="idbmaddcosts" id="idbmaddcosts">
                <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">

                    <tr>
                        <th>额外费用描述<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <input type="text" name="bmcostsdesc" id="bmcostsdesc" class="textInput textbox-width"
                                   style="resize:none;width:200px;height:20px">
                        </td>
                    </tr>

                    <tr>
                        <th>额外费用金额<span class="impSpan">*</span></th>
                        <td style="text-align:left;">
                            <input type="text" name="bmcosts" id="bmcosts"
                                   style="resize:none;width:200px;height:20px" value="0">
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    var oldChangeBmiamountcost = "NaN";
    $(document).ready(function () {

        $("#bmcosts").numberbox({
            precision: "2",
            max: "99999999.99",
            size: "10",
            maxlength: "10",
            onChange: function (newValue, oldValue) {
                oldChangeBmiamountcost = oldValue;
            }
        });


        $('#bmcostlist').datagrid({
                    <c:if test="${bmorder!=null}">
                    url: '<c:url value="/home/bm/findCostsByIdbmorder.do?_csrf=${_csrf.token}"/>&idbmorder=${bmorder.idbmorder}&t=' + new Date().getTime(),
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
                        {field: 'bmcostsdesc', title: '额外费用描述', width: 250, algin: 'center'},

                        {field: 'bmcosts', title: '额外费用金额', width: 50, algin: 'left'}

                    ]],
                    toolbar: [{
                        text: '添加',
                        iconCls: 'icon-add',
                        handler: function () {

                            $('#bmcostDialog').dialog('open');

                            $("#cost_bmorderIdbmorder").val($("#idbmorder").val());
                            $("#bmcostsdesc").val('');
                            $("#idbmaddcosts").val('');
                            $('#bmcosts').numberbox('setValue', 0);


                        }
                    }, '-', {
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {

                            var record = $('#bmcostlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行编辑。', 'info');
                            } else {
                                $('#bmcostDialog').dialog('open');
                                $("#cost_bmorderIdbmorder").val($("#idbmorder").val());
                                $("#idbmaddcosts").val(record.idbmaddcosts);
                                $("#bmcostsdesc").val(record.bmcostsdesc);
                                $('#bmcosts').numberbox('setValue', record.bmcosts);
//
                            }
                            oldChangeBmiamountcost = "NaN";
                        }
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: function () {
                            var record = $('#bmcostlist').datagrid('getSelected');

                            if (record == null) {
                                $.messager.confirm('提示', '请选择某行数据再进行删除。', 'info');
                            } else {
                                $.messager.confirm("操作提示", "您确定要执行删除操作吗？", function (data) {
                                    if (data) {
                                        var bmcosts = record.bmcosts;
                                        $.ajax({
                                            type: "POST",
                                            url: '<c:url value="/home/bm/delBmCostsById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                            dataType: "json",
                                            data: {
                                                idbmaddcosts: record.idbmaddcosts, bmcosts: bmcosts
                                            },
                                            beforeSend: function () {
                                                $.messager.progress({
                                                    text: '请求正在提交中，请稍候...'
                                                });
                                            },
                                            success: function (msg) {
                                                $.messager.progress('close');
                                                if (msg.success == true) {
                                                    $("#bmorderamount").val(($("#bmorderamount").val() - parseFloat(bmcosts)).toFixed(2));
                                                    $.messager.alert('操作成功', '删除成功。', 'info');
                                                    $('#bmcostlist').datagrid('reload');
                                                } else {
                                                    $.messager.alert('操作失败', '删除失败！', 'error');
                                                }
                                                oldChangeBmiamountcost = "NaN";
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
                        $('#bmcostlist').datagrid('resize');
                    },
                    onDblClickRow: function (index, row) {

                    },
                    onSelect:function(index,row){
                        oldChangeBmiamountcost = "NaN";
                    }
                }
        );

        $('#bmcostDialog').dialog({
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
                    if (oldChangeBmiamountcost == "NaN")oldChangeBmiamountcost = $("#bmcosts").val();
                    var changebmorderamount = (parseFloat($("#bmcosts").val()) - parseFloat(oldChangeBmiamountcost) + parseFloat($("#bmorderamount").val())).toFixed(2);

                    var data = $("#costSubform").serializeArray();
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/home/bm/saveBmCostsInfo.do?_csrf=${_csrf.token}"/>&changebmorderamount=' + changebmorderamount + '&t=' + new Date().getTime(),
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
                                $('#bmcostlist').datagrid('reload');
                                $("#bmorderamount").val(changebmorderamount);
                                $('#bmcostDialog').dialog('close');


                            } else {
                                $.messager.alert('操作失败', '保存失败！', 'error');
                            }
                            oldChangeBmiamountcost = "NaN";
                        },
                        error: function (msg) {
                            $.messager.progress('close');
                            $.messager.alert('操作失败', '更新失败！' + msg, 'error');
                        }
                    })
                }
            },
                {
                    text: '关闭',
                    handler: function () {
                        $('#bmcostDialog').dialog('close');
                    }
                }
            ]
        });

    })

</script>
