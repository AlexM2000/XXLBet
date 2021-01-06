<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<head>
    <title><ut:locale_tag key="admin.title"/></title>
</head>
<body>
    <jstl:forEach var="user" items="${users}">

    </jstl:forEach>
</body>
</html>
