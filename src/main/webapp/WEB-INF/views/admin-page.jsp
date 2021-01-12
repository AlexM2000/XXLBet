<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="admin-page.title"/></title>
    <script src="${pageContext.request.contextPath}/js/admin_page.js"></script>
</head>
<body>
    <div class="container my-4 py-4">
        <h3 class="text-info"><ut:locale_tag key="admin-page.form"/></h3>
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
            <label style="display: none" id="tournamentLabel" for="tournamentSelect" class="text-info"><ut:locale_tag key="admin-page.tournamentselect.label"/></label>
            <select id="tournamentSelect" class="form-control" style="display: none" title=<ut:locale_tag key="admin-page.tournamentselect.title"/>></select>
            <small id="noTournamentsInfo" class="text-danger"></small>
        </div>

        <div class="form-group">
            <label id="team1Label" style="display: none" for="team1" class="text-info"><ut:locale_tag key="admin-page.team1select.label"/></label>
            <select id="team1" style="display: none" class="form-control" title=<ut:locale_tag key="admin-page.teamselector1.title"/>></select>
            <small id="team1Informer" class="text-danger"></small>
        </div>

        <div class="form-group">
            <label id="team2Label" style="display: none" for="team2" class="text-info"><ut:locale_tag key="admin-page.team2select.label"/></label>
            <select id="team2" style="display: none" class="form-control" title=<ut:locale_tag key="admin-page.teamselector2.title"/>></select>
        </div>

        <div class="form-group">
            <label style="display: none" for="inputDrawCoefficient"><ut:locale_tag key="admin-page.draw-coefficient"/></label>
            <input type="number" class="form-control" style="display: none" step="0.1" name="drawCoefficient" id="inputDrawCoefficient" placeholder=<ut:locale_tag key="admin-page.draw-coefficient"/> />
            <small id="drawCoefficientInformer" class="text-danger"></small>
        </div>

        <div class="form-group">
            <label style="display: none" for="inputTeam1Coefficient"><ut:locale_tag key="admin-page.team1-coefficient"/></label>
            <input class="form-control" style="display: none" type="number" step="0.1" name="team1Coefficient" id="inputTeam1Coefficient" placeholder=<ut:locale_tag key="admin-page.team1-coefficient"/> />
            <small id="team1CoefficientInformer" class="text-danger"></small>
        </div>

        <div class="form-group">
            <label style="display: none" for="inputTeam2Coefficient"><ut:locale_tag key="admin-page.team2-coefficient"/></label>
            <input class="form-control" style="display: none" type="number" step="0.1" name="team2Coefficient" id="inputTeam2Coefficient" placeholder=<ut:locale_tag key="admin-page.team2-coefficient"/> />
            <small id="team2CoefficientInformer" class="text-danger"></small>
        </div>

        <div class="form-group">
            <label style="display: none" for="dateStarted"><ut:locale_tag key="admin-page.date-started"/></label>
            <input class="form-control" style="display: none" type="datetime-local" id="dateStarted" placeholder=<ut:locale_tag key="admin-page.date-started"/> />
            <small id="dateStartedInformer" class="text-danger"></small>
        </div>

        <div class="form-group">
            <small id="noTeamsInfo" class="text-danger"></small>
        </div>

        <button type="button" class="btn btn-primary"onclick="createMatch()" disabled><ut:locale_tag key="admin-page.button"/></button
    </div>
</body>
</html>
