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
    <div class="eleContainer titleText textS16">
        下单列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="bmlist"></table>
    </div>
</div>


<script type="text/javascript">

    $(document).ready(function () {

        $('#bmlist').datagrid({
                    url: '<c:url value="/home/bm/findAllBmorders.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                    title: '下单列表',
                    pagination: true,
                    fitColumns: true,
                    singleSelect: true,
                    pageSize: 15,
                    pageList: [15, 30, 45],
                    collapsible: true,
                    rownumbers: true,
                    columns: [[
//                        {field:'checked',formatter:function(value,row,index){
//                            if(row.checked){
//                                return '<input type="checkbox" name="DataGridCheckbox" checked="checked">';
//                            }
//                            else{
//                                return '<input type="checkbox" name="DataGridCheckbox">';
//                            }
//                        }},
                        {field: 'bmcusname', title: '客户名称', width: 50},
                        {field: 'bmordernum', title: '订单编号', width: 50},
                        {field: 'product', title: '产品', width: 200},
                        {field: 'bmorderamount', title: '订单金额', width: 50},
                        {
                            field: 'bmbillingdate', title: '开单日期', width: 50,
                            formatter: function (value, row, index) {

                                return formatDataFromNumber(value);
                            }
                        },
                        {
                            field: 'bmbillingdate', title: '交货日期', width: 50,
                            formatter: function (value, row, index) {
                                return formatDataFromNumber(value);
                            }
                        },
                        {
                            field: 'bmstatus', title: '订单状态', width: 50,
                            formatter: function (value, row, index) {
                                var str = "";
                                if (value == 0) {
                                    str = "未提交订单";
                                } else if (value == 1) {
                                    str = "已提交订单";
                                }
                                return str;
                            }
                        },
                        {field: 'ownername', title: '负责人', width: 30, align: 'left'}
                    ]],
                    toolbar: [{
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var record = $('#bmlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行编辑。', 'info');
                            } else {
                                window.location.href='<c:url value="/home/bm/bmindex.do?_csrf=${_csrf.token}"/>&idbmorder='+record.idbmorder+'&t=' + new Date().getTime();
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
                        }
                    }],
                    onLoadSuccess: function () {
                        $('#bmlist').datagrid('resize');
                    },
                    onDblClickRow: function (index, row) {

                    }
                }
        );

    })

</script>
<jsp:include page="./../../footer.jsp"></jsp:include>
</body>
</html>