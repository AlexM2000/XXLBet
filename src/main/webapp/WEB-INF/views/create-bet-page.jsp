<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="create-bet-page.title"/></title>
    <script src="${pageContext.request.contextPath}/js/create_bet_page.js"></script>
</head>
<body>
<div class="container my-4 py-4">
    <h3 class="text-info"><ut:locale_tag key="create-bet-page.form"/></h3>
    <div class="form-group">
        <label for="sportSelect" class="text-info"><ut:locale_tag key="admin-page.sportselect.label"/></label>
        <select id="sportSelect" class="form-control" title=<ut:locale_tag key="admin-page.sportselect.title"/>>
            <option></option>
            <jstl:forEach var="sport" items="${sports}">
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
        <label style="display: none" id="matchLabel" for="matchSelect" class="text-info"><ut:locale_tag key="create-bet-page.matchselect.label"/></label>
        <select id="matchSelect" class="form-control" style="display: none" title=<ut:locale_tag key="create-bet-page.matchselect.title"/>></select>
        <small id="noMatchesInfo" class="text-danger"></small>
        <small class="text-info"><ut:locale_tag key="create-bet-page.draw-coefficient-info"/></small><small id="drawCoefficientInfo" class="text-info"></small>
        <small class="text-info"><ut:locale_tag key="create-bet-page.first-team-coefficient-info"/>: </small><small id="firstTeamCoefficientInfo" class="text-info"></small>
        <small class="text-info"><ut:locale_tag key="create-bet-page.second-team-coefficient-info"/>: </small><small id="secondTeamCoefficientInfo" class="text-info"></small>
    </div>

    <button type="button" class="btn btn-primary"onclick="createBet()" disabled><ut:locale_tag key="create-bet-page.button"/></button>
</div>
</body>
</html>
