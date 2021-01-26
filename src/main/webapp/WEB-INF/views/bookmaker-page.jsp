<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<%@include file="/WEB-INF/views/header.jsp"%>
<head>
    <title><ut:locale_tag key="bookmaker-page.title"/></title>
    <script src="${pageContext.request.contextPath}/js/bookmaker_page.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css"/>
</head>
<body>
        <div id="userTable" class="div-table table table-striped">
        <div class="div-table-row">
            <div class="div-table-col"><ut:locale_tag key="bookmaker-page.email"/></div>
            <div class="div-table-col"><ut:locale_tag key="bookmaker-page.phoneNumber"/></div>
            <div class="div-table-col"><ut:locale_tag key="bookmaker-page.role"/></div>
            <div class="div-table-col"><ut:locale_tag key="bookmaker-page.status"/></div>
        </div>
        <div>
            <jstl:forEach var="user" items="${users}">
                <div class="div-table-row">
                    <div class="div-table-col">${user.getEmail()}</div>
                    <div class="div-table-col">${user.getPhoneNumber()}</div>
                    <div class="div-table-col">${user.getRole()}</div>
                    <div class="div-table-col">${user.getStatus()}</div>
                </div>
                <div class="form-group">
                    <select id="InputRole-${user.getEmail()}" title=<ut:locale_tag key="bookmaker-page.role"/>>
                        <option selected value="client"><ut:locale_tag key="bookmaker-page.role.client"/></option>
                        <option value="admin"><ut:locale_tag key="bookmaker-page.role.admin"/></option>
                        <option value="bookmaker"><ut:locale_tag key="bookmaker-page.role.bookmaker"/></option>
                    </select>
                    <select id="InputStatus-${user.getEmail()}" title=<ut:locale_tag key="bookmaker-page.status"/>>
                        <option selected value="active"><ut:locale_tag key="bookmaker-page.status.active"/></option>
                        <option value="banned"><ut:locale_tag key="bookmaker-page.status.banned"/></option>
                    </select>
                    <button id="changeDataButton" onclick=changeUserRoleAndStatus("${user.getEmail()}")><ut:locale_tag key="bookmaker-page.rolestatusbuttion"/></button>
                </div>
            </jstl:forEach>
        </div>
        </div>
</body>
</html>
