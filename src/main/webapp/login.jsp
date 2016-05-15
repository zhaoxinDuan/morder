<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>仓库管理系统</title>
    <link rel="stylesheet" href="<c:url value="/resources/js/easyui/themes/default/easyui.css"/>" type="text/css" media="screen" />
    <link rel="stylesheet" href="<c:url value="/resources/js/easyui/themes/icon.css"/>" type="text/css" media="screen" />
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>" type="text/css" media="screen" />
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easyui/jquery.easyui.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easyui/locale/easyui-lang-zh_CN.js"/>"></script>
</head>
<body style="visibility:visible">
<div class="easyui-dialog" style="width:500px;height:300px;background:#fafafa;overflow:hidden"
     title="登录系统" closable="false" border="false">
    <div class="header" style="height:60px;">
        <div class="toptitle">仓库管理系统</div>
    </div>
    <div style="padding:60px 0;">
        <form action="<c:url value='/j_security_check.do'/>" method="post">
            <div style="padding-left:150px">
                <table cellpadding="0" cellspacing="3">
                    <tr>
                        <td>登录帐号</td>
                        <td><input name="j_username"></td>
                    </tr>
                    <tr>
                        <td>登录密码</td>
                        <td><input type="password" name="j_password"></td>
                    </tr>
                    <tr>
                        <td>记住:</td>
                        <td><input type="checkbox"
                                   name="remember-me" id="remember-me" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="login" type="submit" value="" style="width:74px;height:21px;border:0"/>
                        </td>
                    </tr>
                </table>
            </div>

        </form>
    </div>
</div>
</body>
</html>