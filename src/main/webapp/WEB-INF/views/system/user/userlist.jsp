<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="fieldDefDiv">
    <div class="eleContainer titleText textS16">
        用户列表
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="userlist"></table>
    </div>
    <div id="userDefDialog">
        <div class="eleContainer elePaddingBtm">
            <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">
                <input type="hidden" id="iduser" name="iduser">
                <input type="hidden" id="idunit" name="idunit" value="${idunit}">
                <tr>
                    <th>姓名<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="urealname" id="urealname" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>登陆名<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="uname" id="uname" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>登陆密码</th>
                    <td style="text-align:left;">
                        <input type="text" name="upwd" id="upwd" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>邮箱</th>
                    <td style="text-align:left;">
                        <input type="text" name="uemail" id="uemail" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>联系电话</th>
                    <td style="text-align:left;">
                        <input type="text" name="umphone" id="umphone" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        var url = '<c:url value="/sys/findAllUnitUsers.do?idunit=${idunit}&_csrf=${_csrf.token}"/>&t=' + new Date().getTime();
        if(${idunit==1}){
            url = '<c:url value="/sys/findAllUsers.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime();
        }
        $('#userlist').datagrid({
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
                        {field: 'urealname', title: '姓名', width: 50},
                        {field: 'uname', title: '登陆名', width: 50},
                        {field: 'uemail', title: '邮箱', width: 50},
                        {field: 'umphone', title: '联系电话', width: 50},
                        {
                            field: 'uisdel', title: '状态', width: 50,
                            formatter: function (value, row, index) {
                                if (value == 0) {
                                    return "正常";
                                } else {
                                    return "禁用";
                                }
                            }
                        }
                    ]],
                    toolbar: [{
                        text: '添加',
                        iconCls: 'icon-add',
                        handler: function () {
                            $('#userDefDialog').dialog('open');
                            $("#iduser").val('');
                            $("#urealname").val('');
                            $("#uname").val('');
                            $("#upwd").val('');
                            $("#uemail").val('');
                            $("#umphone").val('');
                        }
                    }, '-', {
                        text: '编辑',
                        iconCls: 'icon-edit',
                        handler: function () {
                            var record = $('#userlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行编辑。', 'info');
                            } else {
                                $('#userDefDialog').dialog('open');
                                $("#iduser").val(record.iduser);
                                $("#urealname").val(record.urealname);
                                $("#uname").val(record.uname);
                                $("#upwd").val('');
                                $("#uemail").val(record.uemail);
                                $("#umphone").val(record.umphone);
                            }
                        }
                    }, '-', {
                        text: '删除',
                        iconCls: 'icon-remove',
                        handler: function () {
                            var record = $('#userlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行删除。', 'info');
                            } else {
                                $.ajax({
                                    type: "POST",
                                    url: '<c:url value="/sys/delUserById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                    dataType: "json",
                                    data: {iduser: record.iduser},
                                    beforeSend: function () {
                                        $.messager.progress({
                                            text: '请求正在提交中，请稍候...'
                                        });
                                    },
                                    success: function (msg) {
                                        $.messager.progress('close');
                                        if (msg.success == true) {
                                            $.messager.alert('操作成功', '删除成功。', 'info');
                                            $('#userlist').datagrid('reload');
                                        } else {
                                            $.messager.alert('操作失败', '删除失败！', 'error');
                                        }
                                    },
                                    error: function (msg) {
                                        $.messager.progress('close');
                                        $.messager.alert('操作失败', '后台出现异常！'+msg, 'error');
                                    }
                                })
                            }
                        }
                    }, '-', {
                        text: '启用',
                        iconCls: 'icon-ok',
                        handler: function () {
                            var record = $('#userlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行启用。', 'info');
                            } else {
                                $.ajax({
                                    type: "POST",
                                    url: '<c:url value="/sys/userUpdateStatus.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                    dataType: "json",
                                    data: {iduser: record.iduser, uisdel: 0},
                                    beforeSend: function () {
                                        $.messager.progress({
                                            text: '请求正在提交中，请稍候...'
                                        });
                                    },
                                    success: function (msg) {
                                        $.messager.progress('close');
                                        if (msg.success == true) {
                                            $.messager.alert('操作成功', '启用成功。', 'info');
                                            $('#userlist').datagrid('reload');
                                        } else {
                                            $.messager.alert('操作失败', '启用失败！', 'error');
                                        }
                                    },
                                    error: function (msg) {
                                        $.messager.progress('close');
                                        $.messager.alert('操作失败', '后台出现异常！'+msg, 'error');
                                    }
                                })
                            }
                        }
                    }, '-', {
                        text: '禁用',
                        iconCls: 'icon-no',
                        handler: function () {
                            var record = $('#userlist').datagrid('getSelected');
                            if (record == null) {
                                $.messager.alert('提示', '请选择某行数据再进行禁用。', 'info');
                            } else {
                                $.ajax({
                                    type: "POST",
                                    url: '<c:url value="/sys/userUpdateStatus.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                                    dataType: "json",
                                    data: {iduser: record.iduser, uisdel: 1},
                                    beforeSend: function () {
                                        $.messager.progress({
                                            text: '请求正在提交中，请稍候...'
                                        });
                                    },
                                    success: function (msg) {
                                        $.messager.progress('close');
                                        if (msg.success == true) {
                                            $.messager.alert('操作成功', '禁用成功。', 'info');
                                            $('#userlist').datagrid('reload');
                                        } else {
                                            $.messager.alert('操作失败', '禁用失败！', 'error');
                                        }
                                    },
                                    error: function (msg) {
                                        $.messager.progress('close');
                                        $.messager.alert('操作失败', '后台出现异常！'+msg, 'error');
                                    }
                                })
                            }
                        }
                    }],
                    onLoadSuccess: function () {
                        $('#userlist').datagrid('resize');
                    },
                    onDblClickRow: function (index, row) {

                    }
                }
        );

        $('#userDefDialog').dialog({
            collapsible: true,
            minimizable: true,
            maximizable: true,
            cache: false,
            width: 600,
            height: 250,
            title: "用户定义",
            closed: true,
            buttons: [{
                text: '提交',
                iconCls: 'icon-ok',
                handler: function () {

                    if(isMail('uemail','邮件地址',false))return;
                    if(isMobile('umphone','手机号码',false))return;
                    if(isEmpty('urealname','用户名'))return;
                    if(isEmpty('uname','登录名'))return;
                    if(isEmptyJd("urealname")){
                        $.messager.alert("提示",  "【"+note+"】格式错误", "info", function () {
                            obj.focus();
                        });
                        return true;
                    }
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/sys/saveUserInfo.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                        dataType: "json",
                        data: {
                            iduser: $("#iduser").val(),
                            urealname: $("#urealname").val(),
                            uname: $("#uname").val(),
                            upwd: $("#upwd").val(),
                            uemail: $("#uemail").val(),
                            umphone: $("#umphone").val(),
                            idunit: $("#idunit").val()
                        },
                        beforeSend: function () {
                            $.messager.progress({
                                text: '请求正在提交中，请稍候...'
                            });
                        },
                        success: function (msg) {
                            $.messager.progress('close');
                            if (msg.success == true) {
                                $.messager.alert('操作成功', '保存成功', 'info');
                                $('#userlist').datagrid('reload');
                                $('#userDefDialog').dialog('close');
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
            }, {
                text: '关闭',
                handler: function () {
                    $('#userDefDialog').dialog('close');
                }
            }]
        });
    })

</script>
