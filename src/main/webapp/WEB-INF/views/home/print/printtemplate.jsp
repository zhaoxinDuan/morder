<%--
  Created by IntelliJ IDEA.
  User: amis
  Date: 16-5-29
  Time: 下午3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.io.*" %>
<%
    response.reset();
    System.out.println("request.getParameter(\"filepath\") = " + request.getParameter("filepath"));
//    String filepath = new String(request.getParameter("filepath").getBytes("iso-8859-1"), "utf-8");
    String filepath = "/home/amis/project/morder/src/main/webapp/resources/template/temp/ptemplate.xls";
    System.out.println("filepath = " + filepath);
    response.setContentType("application/vnd.ms-excel");
    InputStream ips = new FileInputStream(filepath);  //<---你的excel文件
    OutputStream ops = response.getOutputStream();

    int data = -1;
    while ((data = ips.read()) != -1) {

        ops.write(data);
    }

    ops.flush();
%>
