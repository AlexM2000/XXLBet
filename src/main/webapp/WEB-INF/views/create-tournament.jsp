<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="create.tournament.title"/></title>
    <script src="${pageContext.request.contextPath}/js/create-tournament.js"></script>
</head>
<body>
    <div class="container my-4 py-4">
        <h3 class="text-info"><ut:locale_tag key="create.tournament.form"/></h3>
        <div class="form-group">
            <label for="sportSelect" class="text-info"><ut:locale_tag key="create.tournament.sportselect.label"/></label>
            <select id="sportSelect" class="form-control" title=<ut:locale_tag key="create.tournament.sportselect.title"/>>
                <option></option>
                <jstl:forEach var="sport" items="${requestScope.sports}">
                    <option value="${sport.getId()}">${sport.getName()}</option>
                </jstl:forEach>
            </select>
            <small id="sportInformer" class="text-danger"></small>
        </div>

        <div class="form-group">
            <label style="display: none" id="tournamentNameLabel" for="tournamentName" class="text-info"><ut:locale_tag key="create.tournament.enter.tournament.name"/></label>
            <input style="display: none" id="tournamentName" class="form-control">
            <small id="tournamentNameInformer" class="text-danger"></small>
        </div>
        <button onclick="createTournament()" class="btn btn-info"><ut:locale_tag key="create.tournament.submit"/></button>
    </div>
</body>
</html>
