<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="do.change.password.title"/></title>
    <script src="${pageContext.request.contextPath}/js/do-change-password.js"></script>
</head>
<body>
<div class="container my-5">
    <input hidden id="userId" value="${requestScope.passwordRequest.getUserId()}"/>
    <div class='form-group'>
        <label for='password'><ut:locale_tag key="do.change.password.enter.password"/></label>
        <input type='password' class='form-control' id='password' name='password'>
        <small class="text-danger" id="passwordInformer"></small>
    </div>
    <div class='form-group'>
        <label for='repeatPassword'><ut:locale_tag key="do.change.password.repeat.password"/></label>
        <input type='password' class='form-control' id='repeatPassword' name='password'/>
    </div>
    <button class="btn btn-primary" onclick="change_password()"><ut:locale_tag key="do.change.password.submit"/></button>
</div>
</body>
</html>

