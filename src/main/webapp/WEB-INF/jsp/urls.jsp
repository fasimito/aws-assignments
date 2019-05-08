<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/static/jquery/jquery-3.4.0.js"></script>
    <script type="text/javascript" src="/static/js/urls.js"></script>
    <title>Short URLs</title>
</head>
<body>
<div style="width: 1000px;" >
    <h3 align="center">URLs</h3>
    <table width="100%" border="1" cellspacing="1" cellpadding="0">
        <tr>
            <td>序号</td>
            <td>KEY1</td>
            <td>KEY2</td>
            <td>KEY3</td>
            <td>KEY4</td>
            <td>URL</td>
        </tr>
        <c:forEach items="${urls}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.key1}</td>
                <td>${u.key2}</td>
                <td>${u.key3}</td>
                <td>${u.key4}</td>
                <td>${u.url}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>