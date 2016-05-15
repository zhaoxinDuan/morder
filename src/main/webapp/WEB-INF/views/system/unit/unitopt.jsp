<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="indContent removeHeadi">
    <div class="cLeftHead">机构列表</div>
    <div class="cLeft">
        <div class="eleContainer" style="margin-top:0pt;">
            <ul id="dtree">
            </ul>
            <div id="mm" class="easyui-menu" style="width:120px;">
                <div onclick="append()" data-options="iconCls:'icon-add'">添加部门</div>
                <div onclick="edit()" data-options="iconCls:'icon-edit'">修改部门</div>
                <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除部门</div>
                <div class="menu-sep"></div>
                <div onclick="expand()">展开</div>
                <div onclick="collapse()">折叠</div>
            </div>
        </div>
    </div>
    <div id="arrowH" class="arrowH" onclick="HTelescopic()">
        <div id="arrowHImg" class="arrowHA"></div>
    </div>
    <div class="cRight">
        <iframe id="uFrame" name="uFrame" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
</div>
<div id="menudialog">
    <div class="eleContainer elePaddingBtm">
        <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">
            <tr>
                <th>部门名称<span class="impSpan">*</span></th>
                <td style="text-align:left;">
                    <input type="hidden" name="opttype" id="opttype">
                    <input type="text" name="unitname" id="unitname" class="textInput textbox-width"
                           style="resize:none;width:96%;height:20px">
                </td>
            </tr>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        //一次加载
        $("#dtree").tree({
            url: '<c:url value="/sys/getUnitTree.do?_csrf=${_csrf.token}&t="/>' + new Date().getTime(),
            parentField: "pid",
            textFiled: "text",
            idFiled: "id",
            formatter: function (node) {
                var s = node.text;
                    s = '<a href="<c:url value="/sys/unitindex.do"/>?idunit=' + node.id + '&t=' + new Date().getTime() + '" target="uFrame">' + s + '</a>';
                return s;
            },
            animate: true,
            onContextMenu: function (e, node) {
                e.preventDefault();
                $(this).tree('select', node.target);
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                })
            },
            onLoadSuccess: function (node, data) {
                $(this).tree('expandAll')
            }
        });

        $('#menudialog').dialog({
            collapsible: true,
            minimizable: true,
            maximizable: true,
            cache: false,
            width: 600,
            height: 250,
            title: "节点名称录入",
            closed: true,
            buttons: [{
                text: '提交',
                iconCls: 'icon-ok',
                handler: function () {
                    var node = $('#dtree').tree('getSelected');
                    var opttype = $("#opttype").val();
                    var data;
                    if(opttype==0){
                        data = {
                            unitname: $("#unitname").val(),
                            pidunit: node.id
                        }
                    }else if(opttype==1){
                        data = {
                            unitname: $("#unitname").val(),
                            idunit: node.id,
                            pidunit: node.pid
                        }
                    }else{
                        $.messager.alert('数据错误', '数据错误！', 'error');
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: '<c:url value="/sys/saveunit.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
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
                                $('#dtree').tree("reload");
                                $('#menudialog').dialog('close');
                            } else {
                                $.messager.alert('操作失败', '保存失败！', 'error');
                            }
                        },
                        error:function(data, status, e){
                            $.messager.alert('操作失败', '后台出现错误！'+e, 'error');
                            $.messager.progress('close');
                        }
                    })
                }
            }, {
                text: '关闭',
                handler: function () {
                    $('#menudialog').dialog('close');
                }
            }]
        });
    });
    $(window).resize(function () {
    });
    function append() {
        $("#opttype").val(0);
        $("#unitname").val("");
        $('#menudialog').dialog('open');
    }
    function edit() {
        $("#opttype").val(1);
        var node = $('#dtree').tree('getSelected');
        $("#unitname").val(node.text);
        $('#menudialog').dialog('open');
    }
    function removeit() {
        var node = $('#dtree').tree('getSelected');
        if (node.pdtid == "0") {
            $.messager.alert('不能删除公司！', '不能删除公司！', 'info');
            return;
        }
        if (node.children) {
            $.messager.alert('请先删除部门！', '请先删除部门！', 'info');
            return;
        }
        $.ajax({
            type: "POST",
            url: '<c:url value="/sys/removeUnitsById.do?_csrf=${_csrf.token}"/>&t=' + new Date().getTime(),
            dataType: "json",
            data: {
                idunit: node.id
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
                    $('#dtree').tree("reload");
                } else {
                    $.messager.alert('操作失败', '保存失败！', 'error');
                }
            },
            error:function(data, status, e){
                $.messager.alert('操作失败', '后台出现错误！'+e, 'error');
                $.messager.progress('close');
            }
        })

    }
    function collapse() {
        var node = $('#dtree').tree('getSelected');
        $('#dtree').tree('collapse', node.target);
    }
    function expand() {
        var node = $('#dtree').tree('getSelected');
        $('#dtree').tree('expand', node.target);
    }
</script>
