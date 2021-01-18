<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/AJAXScripts.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dropdown.css">
</head>
<body>
<div class="header" id="headerId">
    <nav class="navbar navbar-expand-lg navbar-light bg-transparent my-1">
        <a class="navbar-brand" href="home"></a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/articles/"></a>
                </li>
                <jstl:choose>
                    <jstl:when test="${sessionScope.login ne null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="/xxlbet?command=profile"><ut:locale_tag key="header.profile"/></a>
                        </li>
                        <jstl:if test="${sessionScope.role.getName() == 'admin'}">
                            <li class="nav-item active">
                                <a onclick="myFunction()" class="dropbtn nav-link"><ut:locale_tag key="header.admin"/>
                                    <div id="myDropdown" class="dropdown-content">
                                        <a href="${pageContext.request.contextPath}/xxlbet?command=admin_page"><ut:locale_tag key="header.create.match"/></a>
                                        <a href="${pageContext.request.contextPath}/xxlbet?command=create_sport_page"><ut:locale_tag key="header.create.sport"/></a>
                                    </div>
                                </a>
                            </li>
                        </jstl:if>
                        <jstl:if test="${sessionScope.role.getName() == 'bookmaker'}">
                            <li class="nav-item active">
                                <a onclick="myFunction()" class="dropbtn nav-link"><ut:locale_tag key="header.admin"/>
                                    <div id="myDropdown" class="dropdown-content">
                                        <a href="${pageContext.request.contextPath}/xxlbet?command=admin_page"><ut:locale_tag key="header.create.match"/></a>
                                        <a href="${pageContext.request.contextPath}/xxlbet?command=create_sport_page"><ut:locale_tag key="header.create.sport"/></a>
                                        <a href="${pageContext.request.contextPath}/xxlbet?command=create_tournament_page"><ut:locale_tag key="header.create.tournament"/></a>
                                        <a href="${pageContext.request.contextPath}/xxlbet?command=create_team_page"><ut:locale_tag key="header.create.team"/></a>
                                    </div>
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="/xxlbet?command=bookmaker_page"><ut:locale_tag key="bookmaker-page.title"/></a>
                            </li>
                        </jstl:if>
                        <li class="nav-item active">
                            <a class="nav-link" href="#" onclick="out()"><ut:locale_tag key="header.out"/></a>
                        </li>
                    </jstl:when>
                    <jstl:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/xxlbet?command=login_page"><ut:locale_tag key="header.login"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/xxlbet?command=registration_page"><ut:locale_tag key="header.register"/></a>
                        </li>
                    </jstl:otherwise>
                </jstl:choose>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/xxlbet?command=home"><ut:locale_tag key="header.home"/></a>
                </li>
            </ul>
        </div>
        <form id="search" action="" onsubmit="">
        <nav class="navbar navbar-light bg-transparent">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <img src="/images/ttplay.jpg" width="60" class="d-inline-block img-fluid">
                <h1>XXLBet</h1>
            </a>
        </nav>
        </form>
        <label for="lang" class="text-info"><ut:locale_tag key="header.current.lang"/>: ${cookie.language.value}</label>
        <div class="form-group">
        <select onclick="languageSelector()" class="my-3" id="lang">
            <option value="en">ENG</option>
            <option value="ru">RUS</option>
            <option value="be">BLR</option>
        </select>
        </div>
    </nav>
</div>
</body>
</html>

