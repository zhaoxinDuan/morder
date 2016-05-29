<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="fieldDefDiv">
    <div class="eleContainer titleText textS16">
        直接下单
    </div>

    <div class="eleContainer elePaddingBtm">
        <form id="morderform">
            <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">

                <tr>
                    <th>客户名称<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="hidden" id="idbmorder" name="idbmorder">
                        <input type="hidden" id="bmcreateuserid" name="bmcreateuserid" value="${iduser}">
                        <input id="tcustomerIdcustomer" name="tcustomerIdcustomer" class="easyui-combobox"
                               data-options="editable:false "
                               style="width:200px;"/>
                    </td>
                    <th>订单编号</th>
                    <td style="text-align:left;">
                        <input type="text" name="bmordernum" id="bmordernum" readonly class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>开单日期<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input id="bmbillingdate" name="bmbillingdate" type="text" class="easyui-datebox"
                               required="required"
                               style="resize:none;width:96%;height:20px">

                    </td>
                    <th>交货日期</th>
                    <td style="text-align:left;">
                        <input type="text" name="bmdeliverydate" id="bmdeliverydate" class="easyui-datebox"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>付款方式<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="bmpaymethod" id="bmpaymethod" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">

                    </td>
                    <th>额外费用</th>
                    <td style="text-align:left;">
                        <input type="text" name="bmaddcosts" id="bmaddcosts" value="0">
                    </td>
                </tr>
                <tr>
                    <th>订单金额<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="bmorderamount" id="bmorderamount" readonly value="0"
                               class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                    <th>包装要求</th>
                    <td style="text-align:left;">
                        <input type="text" name="bmpacreq" id="bmpacreq" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>负责人<span class="impSpan">*</span></th>
                    <td style="text-align:left;" colspan="3">
                        <input id="tuserIduser" name="tuserIduser" class="easyui-combobox"
                               data-options="editable:false "
                               style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <th>备注<span class="impSpan">*</span></th>
                    <td style="text-align:left;" colspan="3">
                            <textarea type="text" name="bmcomments" id="bmcomments" rows="10" class="textArea"
                                      style="resize:none;width: 96%;"></textarea>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;" colspan="4">
                        <a data-options="iconCls:'icon-save'" href="javascript:void(0)" class="easyui-linkbutton"
                           id="savebmorder">保存</a>&nbsp;&nbsp;&nbsp;
                        <a data-options="iconCls:'icon-save'" href="javascript:void(0)" class="easyui-linkbutton"
                           id="postbmorder">完成订单</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>


<script type="text/javascript">
    function init() {
        <c:if test="${bmorder!=null}">
        <c:if test="${bmorder.idbmorder!=null}">
        $("#idbmorder").val("${bmorder.idbmorder}");
        </c:if>
        <c:if test="${bmorder.bmordernum!=null}">
        $("#bmordernum").val("${bmorder.bmordernum}");
        </c:if>

        <c:if test="${bmorder.bmpaymethod!=null}">
        $("#bmpaymethod").val("${bmorder.bmpaymethod}");
        </c:if>
        <c:if test="${bmorder.bmaddcosts!=null}">
        $("#bmaddcosts").val("${bmorder.bmaddcosts}");
        </c:if>
        <c:if test="${bmorder.bmcomments!=null}">
        $("#bmcomments").val("${bmorder.bmcomments}");
        </c:if>
        <c:if test="${bmorder.bmpacreq!=null}">
        $("#bmpacreq").val("${bmorder.bmpacreq}");
        </c:if>
        <c:if test="${bmorder.bmorderamount!=null}">
        $("#bmorderamount").val("${bmorder.bmorderamount}");
        </c:if>

        <c:if test="${bmorder.bmbillingdate!=null}">
        <%--$("#bmbillingdate").val("${bmorder.bmbillingdate}");--%>
        $('#bmbillingdate').datebox("setValue", "${bmorder.bmbillingdate}");
        </c:if>
        <c:if test="${bmorder.bmdeliverydate!=null}">
        $('#bmdeliverydate').datebox("setValue", "${bmorder.bmdeliverydate}");
        <%--$("#bmdeliverydate").val("${bmorder.bmdeliverydate}");--%>
        </c:if>
        <c:if test="${bmorder.tuserIduser!=null}">
        $('#tuserIduser').combobox("setValue", "${bmorder.tuserIduser}");
        </c:if>
        <c:if test="${bmorder.tcustomerIdcustomer!=null}">
        $('#tcustomerIdcustomer').combobox("setValue", "${bmorder.tcustomerIdcustomer}");
        </c:if>

        </c:if>
    }

    function judgeNumber(s) {
        if (!isNaN(s) && s != "" && s != null && s != "null") {
            return true;
        } else {
            return false;
        }
    }

    function saveMorder(ispost) {
//        if (isEmptyEasyUI('bmbillingdate','开单日期'))return;
        if (isEmptyEasyUI('tcustomerIdcustomer', '客户名称'))return;
        var data = $("#morderform").serializeArray();
        $.ajax({
            type: "POST",
            url: '<c:url value="/home/bm/saveMorderInfo.do?_csrf=${_csrf.token}"/>&ispost=' + ispost + '&t=' + new Date().getTime(),
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
                    $('#idbmorder').val(msg.idbmorder);
                    $('#bmordernum').val(msg.bmordernum);
                    $.messager.alert('操作成功', '保存成功。', 'info');
                    if(!ispost){
                        $("#bmitemlist").datagrid({url: '<c:url value="/home/bm/findItemsByIdbmorder.do?_csrf=${_csrf.token}"/>&idbmorder=' + msg.idbmorder + '&t=' + new Date().getTime()});
                    }else{
                        window.location.href="<c:url value="/home/bm/bmlist.do"/>";
                    }

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
    $(document).ready(function () {
        <c:if test="${bmorder==null}">
        $("#bmitemlistDiv").hide();
        </c:if>
        $("#savebmorder").bind("click", function () {
            $("#bmitemlistDiv").show();
            saveMorder(false);

        });
        $("#postbmorder").bind("click", function () {
            saveMorder(true);
        });
        $('#tuserIduser').combobox({
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
                $('#tuserIduser').combobox("setValue", "${iduser}");

            },
            onChange: function (newValue, oldValue) {

            }
        });

        $('#tcustomerIdcustomer').combobox({
            panelHeight: 'auto',
            editable: true,
            required: "true",
            valueField: 'idcustomer',
            textField: 'cname',
            url: '<c:url value="/home/cus/findAllCustomersNolimit.do?_csrf=${_csrf.token}"/>&t='+new Date().getTime,
            filter: function (q, row) {
                var opts = $(this).combobox('options');
                return row[opts.textField].indexOf(q) >= 0;//这里改成>=即可在任意地方匹配
            },
            onLoadSuccess: function (msg) {


            },
            onChange: function (newValue, oldValue) {

            }
        });

        init();

        $("#bmaddcosts").numberbox({
            precision: "2",
            max: "99999999.99",
            size: "10",
            maxlength: "10",
            onChange: function (newValue, oldValue) {
                var bmorderamount = $("#bmorderamount").val();
                var temp_bmorderamount = 0;
                if (judgeNumber(bmorderamount)) {
                    temp_bmorderamount = ((parseFloat(bmorderamount)-parseFloat(oldValue)+parseFloat(newValue)).toFixed(2));

                } else {
                    temp_bmorderamount = newValue;
                }
                $("#bmorderamount").val(temp_bmorderamount);
            }
        });

    })

</script>
<jsp:include page="./bmitemlist.jsp"/>