<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="admin-page.title"/></title>
</head>
<body>
    <div class="form-group">
        <select id="sportSelect" title=<ut:locale_tag key="admin-page.sportselect.title"/>>
            <jstl:forEach var="sport" items="${sports}">
                <option value="${sport.getName()}">${sport.getName()}</option>
            </jstl:forEach>
        </select>
    </div>
</body>
</html>
