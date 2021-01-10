<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><ut:locale_tag key="login.title"/></title>
</head>

<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<div class='container my-3'>
    <form class='my-5' id="authentication">
        <div class='form-group h-75'>
            <label for='InputLogin'><ut:locale_tag key="login.login"/></label>
            <input type='text' class='form-control' id='InputLogin' name='login' aria-describedby='emailHelp' placeholder=<ut:locale_tag key="login.login"/>>
            <small class="text-danger" id="loginError"></small>
        </div>
        <div class='form-group'>
            <label for='InputPassword'><ut:locale_tag key="login.password"/></label>
            <input type='password' class='form-control' id='InputPassword' name='password' placeholder=<ut:locale_tag key="login.password"/>>
            <small class="text-danger" id="passError"></small>
            <small class="text-info"><a href="${pageContext.request.contextPath}/forgot"><ut:locale_tag key="login.forgotpassword"/></a></small>
        </div>
    </form>
    <button type='button' class='btn btn-primary' onclick="login()"><ut:locale_tag key="login.submit"/></button>
</div>
</body>
</html>

