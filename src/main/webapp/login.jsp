<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>永望业务信息管理系统</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/metro_blue/style.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/metro_blue/main.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/images/metro_blue/imagesUrl.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugin/jquery-easyui-1.3.5/themes/metro_blue/easyui-index.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugin/jquery-easyui-1.3.5/themes/metro_blue/icon.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/plugin/jquery-easyui-1.3.5/jquery.easyui.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/plugin/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"/>"></script>
</head>
<body class="loginPageBody">
<div class="ctrlDiv" style="width: 100%;margin-left: 0;">
    <div class="imageBlk">
        <div class="bgBlk">
            <img src="<c:url value="/resources/images/metro_blue/main/login.png"/>" style="width: 100%;height: 100%;">
        </div>
    </div>
    <div class="loginBlk">
        <div class="loginLogo"></div>
        <div class="loginTit">永望业务信息管理系统</div>
        <div class="loginCtrl">
            <div class="mid_ctrl">
                <form id="subForm" action="<c:url value="/j_security_check.do"/>?_csrf=${_csrf.token}" method="post">
                    <div>
                        <input id="j_username" name="j_username" type="text" value="admin" class="inputText"/>
                    </div>
                    <div>
                        <input id="passwordArt" type="text" class="inputText">
                        <input id="j_password" name="j_password" type="password" value="123456" class="inputText" style="display:none;"/>
                    </div>
                    <div>
                        记住:<input type="checkbox" name="remember-me" id="remember-me" />
                    </div>
                    <div style="margin-top: 30px;width: 100%;text-align: left;">
                        <input id="loginBtn" class="loginBtn" type="submit" style="width: 60px;height:40px;" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="login" href="#">忘记密码</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    function validate(){
        var username = document.getElementById("j_username").value;
        if(username == ""){
            $.messager.alert('提示', '用户名不能为空！', 'info');
            document.getElementById("j_username").select();
            return;
        }
        var password = document.getElementById("j_password").value;
        if(password == ""){
            $.messager.alert('提示', '密码不能为空！', 'info');
            document.getElementById("j_password").select();
            return;
        }

        $('#loginBtn').submit();

    }
    function keydown(e){
        var e = e||event;
        var keycode = e.keyCode ||e.which || e.charCode;
        if(keycode == 13){
            validate();
        }
    }

    document.onkeydown = keydown;


    if("1"=="${error}"){
        $.messager.alert('提示', '用户名和密码错误！', 'info');
    }

    function textValFill(id, valTips) {
        $('#' + id).val(valTips).focus(function () {
            if ($(this).val() == valTips) {
                $(this).val('');
            }
        }).blur(function () {
            if ($(this).val() == '') {
                $(this).val(valTips);
            }
        });
    }

    function pwdValFill(disguiseId, id, valTips) {
        $('#' + disguiseId).val(valTips).focus(function () {
            $(this).hide();
            $('#' + id).show().focus();
        });
        $('#' + id).blur(function () {
            if ($(this).val() == '' || $(this).val() == undefined) {
                $(this).hide();
                $('#' + disguiseId).show();
            }
        });
    }

    $(function () {
        var userName = '用户名';
        var pwdTips = '登录密码';
        textValFill('j_username', userName);
        pwdValFill('passwordArt', 'j_password', pwdTips);

    });
</script>
</html>