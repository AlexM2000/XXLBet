<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="change.passsword.title"/></title>
    <script src="${pageContext.request.contextPath}/js/change-password-request.js"></script>
</head>
<body>
<div class="container my-5">
    <div class="form-group">
        <div class="form-group">
            <label for="email"><ut:locale_tag key="change.passsword.enter.email"/></label>
            <input type="text" class="form-control" id="email"/>
            <small class="text-danger" id="emailInformer"></small>
        </div>
        <button type="button" class="btn btn-primary" onclick="create_change_password_request()"><ut:locale_tag key="change.passsword.submit"/></button>
    </div>
</div>
</body>
</html>
