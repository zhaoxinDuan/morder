<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="./header.jsp"></jsp:include>
</head>
<body>
<div class="indHead">
    <div class="logo"><h1>永望订单管理系统</h1></div>
    <div class="func">
        <div class="funcIco icoUser"></div>
        <div class="funcText">${tUser.urealname}</div>
        <div class="funcIco icoInfo"></div>
        <div class="funcText"><a href="javascript:void(0)" id="modifypwda">修改密码</a></div>
        <div class="funcIco icoUser"></div>
        <div class="funcText"><a href="<c:url value="/logout.do"/>">退出</a></div>
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
                    <a href="<c:url value="/home/bm/bmindex.do"/>?iduser=${tUser.iduser}" target="cFrame">直接下单</a>
                </li>
                <li>
                    <a href="<c:url value="/home/bm/bmlist.do"/>?iduser=${tUser.iduser}" target="cFrame">跟单列表</a>
                </li>
                <li>
                    <a href="<c:url value="/home/bm/bmlistdetail.do"/>?iduser=${tUser.iduser}" target="cFrame">订单明细</a>
                </li>
                <li>
                    <a href="<c:url value="/home/cus/cusindex.do"/>?iduser=${tUser.iduser}" target="cFrame">客户关系管理</a>
                </li>
                <li>
                    <a href="<c:url value="/sys/unitindex.do"/>?iduser=${tUser.iduser}" target="cFrame">人员管理</a>
                </li>
            </ul>

        </div>
    </div>

    <div id="arrowH" class="arrowH" onclick="HTelescopic()">
        <div id="arrowHImg" class="arrowHA"></div>
    </div>
    <div class="cRight">
        <iframe id="cFrame" name="cFrame" src="<c:url value="/home/bm/bmlist.do"/>?iduser=${tUser.iduser}" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
</div>
<jsp:include page="./modifypwd.jsp"></jsp:include>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>

