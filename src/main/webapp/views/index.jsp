<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@taglib prefix="mytags" uri="/WEB-INF/mytags" %>
<%@include file="header.jsp"%>
<title><mytags:locale_tag key="home.title"/></title>
</head>

<div class="container text-justify">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><mytags:locale_tag key="home.league"/></th>
            <th scope="col"><mytags:locale_tag key="home.teams"/></th>
            <th scope="col"><mytags:locale_tag key="home.date-started"/></th>
            <th scope="col"><mytags:locale_tag key="home.draw-coefficient"/></th>
        </tr>
        </thead>
        <tbody>
        <jstl:forEach var="match" items="${matches}">
            <tr>
                <td> ${match.getTournamentName()} </td>
                <td>
                <jstl:forEach var="opponent" items="${match.getOpponents()}">
                    ${opponent.getName()} -
                    <mytags:locale_tag key="home.coefficient"/> ${opponent.getCoefficient()}
                </jstl:forEach>
                </td>
                <jstl:choose>
                    <jstl:when test="${today.isBefore(match.getDateStarted())}">
                        <td> ${mytags:formatLocalDateTime(match.getDateStarted(), 'dd.MM.yyyy HH:mm:ss')} </td>
                    </jstl:when>
                    <jstl:otherwise>
                        <td><mytags:locale_tag key="home.live-now"/></td>
                    </jstl:otherwise>
                </jstl:choose>
                <td> ${match.getDrawCoefficient()} </td>
                <td><a class="alert-info" href="/bet"><mytags:locale_tag key="home.create.bet"/></a></td>
            </tr>
        </jstl:forEach>
        </tbody>
    </table>
</div>