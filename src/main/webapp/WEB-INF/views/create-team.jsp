<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><ut:locale_tag key="create.team.title"/></title>
    <script src="${pageContext.request.contextPath}/js/create-team.js"></script>
</head>
<body>
<div class="container my-4 py-4">
    <h3 class="text-info"><ut:locale_tag key="create.team.title"/></h3>

    <div class="form-group">
        <label for="sportSelect" class="text-info"><ut:locale_tag key="admin-page.sportselect.label"/></label>
        <select id="sportSelect" class="form-control" title=<ut:locale_tag key="admin-page.sportselect.title"/>>
            <option></option>
            <jstl:forEach var="sport" items="${requestScope.sports}">
                <option value="${sport.getName()}">${sport.getName()}</option>
            </jstl:forEach>
        </select>
    </div>

    <div class="form-group">
        <label id="tournamentLabel" for="tournamentSelect" class="text-info"><ut:locale_tag key="admin-page.tournamentselect.label"/></label>
        <select id="tournamentSelect" class="form-control" title=<ut:locale_tag key="admin-page.tournamentselect.title"/>>
            <option></option>
        </select>
        <small id="noTournamentsInfo" class="text-danger"></small>
    </div>

    <div class="form-group">
        <label id="teamNameLabel" for="teamName" class="text-info"><ut:locale_tag key="create.team.enter.team.name"/></label>
        <input id="teamName" class="form-control">
        <small id="teamNameInformer" class="text-danger"></small>
    </div>
    <button onclick="createTeam()" class="btn btn-info"><ut:locale_tag key="create.team.submit"/></button>

</div>
<script src="${pageContext.request.contextPath}/js/create-team.js"></script>
</body>
</html>
