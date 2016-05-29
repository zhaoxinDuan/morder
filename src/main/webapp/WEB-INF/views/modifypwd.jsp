<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<div class="ctrlContainer" id="modifypwdDiv">
    <div id="modifypwdDialog">
        <div class="eleContainer elePaddingBtm">
            <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">
                <input type="hidden" id="iduser" name="iduser" value="${tUser.iduser}">
                <tr>
                    <th>原密码</th>
                    <td style="text-align:left;">
                        <input type="password" name="oldupwd" id="oldupwd" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>新密码</th>
                    <td style="text-align:left;">
                        <input type="password" name="newupwd1" id="newupwd1" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>确认新密码</th>
                    <td style="text-align:left;">
                        <input type="password" name="newupwd2" id="newupwd2" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>

            </table>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $("#modifypwda").bind("click", function () {
            $('#modifypwdDialog').dialog('open');

        });

        $('#modifypwdDialog').dialog({
            collapsible: true,
            minimizable: true,
            maximizable: true,
            cache: false,
            width: 600,
            height: 250,
            title: "密码修改",
            closed: true,
            buttons: [{
                text: '提交',
                iconCls: 'icon-ok',
                handler: function () {
                    var oldupwd = $("#oldupwd").val();
                    var newupwd1 = $("#newupwd1").val();
                    var newupwd2 = $("#newupwd2").val();
                    if(oldupwd==newupwd1){
                        $.messager.alert('提示', '新密码不能和原密码相同！', 'error');
                        return;
                    }
                    if(newupwd1!=newupwd2){
                        $.messager.alert('提示', '两次输入的新密码不相同！', 'error');
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/sys/modifypwd.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
                        dataType: "json",
                        data: {
                            iduser: $("#iduser").val(),
                            oldupwd: oldupwd,
                            newupwd1: newupwd1,
                            newupwd2: newupwd2
                        },
                        beforeSend: function () {
                            $.messager.progress({
                                text: '请求正在提交中，请稍候...'
                            });
                        },
                        success: function (msg) {
                            $.messager.progress('close');
                            if (msg.success == true) {
                                $.messager.alert('操作成功', '操作成功', 'info');
                                $('#modifypwdDialog').dialog('close');
                            } else {
                                $.messager.alert('操作失败', '操作失败！', 'error');
                            }
                        },
                        error: function (msg) {
                            $.messager.progress('close');
                            $.messager.alert('操作失败', '删除失败！'+msg, 'error');
                        }
                    })
                }
            }, {
                text: '关闭',
                handler: function () {
                    $('#modifypwdDialog').dialog('close');
                }
            }]
        });
    })

</script>
