<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ctrlContainer" id="fieldDefDiv">
    <div class="eleContainer titleText textS16">
        直接下单
    </div>
    <div class="eleContainer elePaddingBtm">
        <table id="cuslist"></table>
    </div>
    <div id="cusDefDialog">
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
                            <input type="text" name="cname" id="cname" class="textInput textbox-width"
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

    })

</script>
