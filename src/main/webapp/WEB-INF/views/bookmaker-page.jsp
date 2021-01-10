<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<html>
<%@include file="/WEB-INF/views/header.jsp"%>
<head>
    <title><ut:locale_tag key="bookmaker-page.title"/></title>
    <script src="${pageContext.request.contextPath}/js/bookmaker_page.js"></script>
</head>
<body>
        <table id="betsTable" class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><ut:locale_tag key="bookmaker-page.email"/></th>
            <th scope="col"><ut:locale_tag key="bookmaker-page.phoneNumber"/></th>
            <th scope="col"><ut:locale_tag key="bookmaker-page.role"/></th>
            <th scope="col"><ut:locale_tag key="bookmaker-page.status"/></th>
        </tr>
        </thead>
        <tbody id="betsBody">
            <jstl:forEach var="user" items="${users}">
                <tr>
                    <td>${user.getEmail()}</td>
                    <td>${user.getPhoneNumber()}</td>
                    <td>${user.getRole()}</td>
                    <td>${user.getStatus()}</td>
                </tr>
                <div class="form-group">
                    <select id="InputRole" title=<ut:locale_tag key="bookmaker-page.role"/>>
                        <option selected value="client"><ut:locale_tag key="bookmaker-page.role.client"/></option>
                        <option value="admin"><ut:locale_tag key="bookmaker-page.role.admin"/></option>
                    </select>
                    <select id="InputStatus" title=<ut:locale_tag key="bookmaker-page.status"/>>
                        <option selected value="active"><ut:locale_tag key="bookmaker-page.status.active"/></option>
                        <option value="banned"><ut:locale_tag key="bookmaker-page.status.banned"/></option>
                    </select>
                    <button id="changeDataButton" onclick=changeUserRoleAndStatus("${user.getEmail()}")><ut:locale_tag key="bookmaker-page.rolestatusbuttion"/></button>
                </div>
            </jstl:forEach>
        </tbody>
        </table>
</body>
</html>
