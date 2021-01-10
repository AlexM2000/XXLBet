<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="admin-page.title"/></title>
    <script src="${pageContext.request.contextPath}/js/admin_page.js"></script>
</head>
<body>
    <div class="form-group">
        <select id="sportSelect" title=<ut:locale_tag key="admin-page.sportselect.title"/>>
            <jstl:forEach var="sport" items="${sports}">
                <option value="${sport.getName()}">${sport.getName()}</option>
            </jstl:forEach>
        </select>
        <select id="tournamentSelect" style="display: none" title=<ut:locale_tag key="admin-page.tournamentselect.title"/>>

        </select>
        <small id="noTournamentsInfo" class="text-info"></small>
        <select id="team1" style="display: none" title=<ut:locale_tag key="admin-page.teamselector1.title"/>></select>
        <select id="team2" style="display: none" title=<ut:locale_tag key="admin-page.teamselector2.title"/>></select>
        <small id="noTeamsInfo" class="text-info"></small>
        <button>Submit</button>
    </div>
</body>
</html>
