<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 26.03.2020
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<div class="info">
    <div class="text-center">
        <ut:locale_tag key="expired.token"/>
    </div>
</div>
</body>
</html>
