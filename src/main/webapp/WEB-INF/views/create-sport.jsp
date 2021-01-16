<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="create.sport.title"/></title>
    <script src="${pageContext.request.contextPath}/js/create-sport.js"></script>
</head>
<body>
    <div class="form-group my-4 py-4">
        <label for="sportName"><ut:locale_tag key="create.sport.enter.sport.name"/></label>
        <input id="sportName" class="form-control">
        <small id="sportNameInformer" class="text-danger"></small>
        <button class="btn btn-info"><ut:locale_tag key="create.sport.submit"/></button>
    </div>
</body>
</html>
