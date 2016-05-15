<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html style="height:100%">
<head>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body style="margin:0;padding:0;height:100%;overflow:hidden;background:#F2FBFF">
<div class="mainwrap">
    <div id="mainlayout" class="easyui-layout" fit="true">
        <div region="north" border="false" style="height:94px;background:#A1C4ff">
            <jsp:include page="north.jsp"></jsp:include>
        </div>
        <div region="center" border="false" style="overflow:hidden">
        </div>
    </div>
</div>
</body>
</html>
