<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="./header.jsp"></jsp:include>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="indHead">
    <div class="logo"></div>
    <div class="func">
        <div class="funcIco icoInfo"></div>
        <div class="funcText">
        </div>
        <div class="funcIco icoUser"></div>
        <div class="funcText"><a href="#"></a></div>
        <div class="funcIco icoUser"></div>
        <div class="funcText"><a href="<c:url value="/logout.do"/>">注销</a></div>
    </div>
</div>
<div id="arrowV" class="arrowV" onclick="VTelescopic()">
    <div id="arrowVImg" class="arrowVW"></div>
</div>
<div class="indContent">
    <div class="cLeftHead">功能菜单</div>
    <div class="cLeft">
        <div class="eleContainer" style="margin-top:0;">
            <ul class="easyui-tree">
                <li>
                    <a href="<c:url value="/sys/unitindex.do"/>" target="cFrame">直接下单</a>
                </li>
                <li>
                    <a href="<c:url value="/sys/unitindex.do"/>" target="cFrame">跟单列表</a>
                </li>
                <li>
                    <a href="<c:url value="/sys/unitindex.do"/>" target="cFrame">订单明细</a>
                </li>
                <li>
                    <a href="<c:url value="/sys/unitindex.do"/>" target="cFrame">客户关系管理</a>
                </li>
                <li>
                    <a href="<c:url value="/sys/unitindex.do"/>" target="cFrame">部门人员管理</a>
                </li>

            </ul>

        </div>
    </div>

    <div id="arrowH" class="arrowH" onclick="HTelescopic()">
        <div id="arrowHImg" class="arrowHA"></div>
    </div>
    <div class="cRight">
        <iframe id="cFrame" name="cFrame" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
</div>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>

