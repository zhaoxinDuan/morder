<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="fieldDefDiv">
    <div class="eleContainer titleText textS16">
        直接下单
    </div>

    <div class="eleContainer elePaddingBtm">
        <form id="subform">
            <table class="layoutTable" cellspacing="1" cellpadding="0" border="0">
                <tr>
                    <th>客户名称<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="cname" id="cname" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                    <th>订单编号<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="bmordernum" id="bmordernum" class="textInput textbox-width"
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
                        <input type="text" name="bmdeliverydate" id="bmdeliverydate" class="textInput textbox-width"
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
                        <input type="text" name="bmaddcosts" id="bmaddcosts" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
                </tr>
                <tr>
                    <th>订单金额<span class="impSpan">*</span></th>
                    <td style="text-align:left;">
                        <input type="text" name="bmorderamount" id="bmorderamount" class="textInput textbox-width"
                               style="resize:none;width:96%;height:20px">
                    </td>
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
                           id="postbmorder">提交</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>


<script type="text/javascript">

    $(document).ready(function () {

        $("#savebmorder").bind("click", function () {

        });
        $("#postbmorder").bind("click", function () {

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
                $('#tuserIduser').combobox("setValue", "${tUser.iduser}");

            },
            onChange: function (newValue, oldValue) {

            }
        });


    })

</script>
<jsp:include page="./bmitemlist.jsp"/>