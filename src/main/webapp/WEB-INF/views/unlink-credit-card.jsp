<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="unlink.credit.card.title"/></title>
    <script src="${pageContext.request.contextPath}/js/unlink_credit_card.js"></script>
</head>
<body>

<div class="container my-5">
    <h3 class="text-info"><ut:locale_tag key="unlink.credit.card.title"/></h3>
    <div class="form-group">
        <label for="userId"><ut:locale_tag key="unlink.credit.card.choose"/></label>
        <input hidden id="userId" value="${sessionScope.user_id}"/>
        <select id="chooseCard" class="form-control-sm">
            <jstl:forEach var="card" items="${cards}">
                <option value="${card.getNumber()}">${card.getNumber()} - ${card.getThru()}</option>
            </jstl:forEach>
        </select>
    </div>
    <button class="btn btn-primary" onclick="unlink_credit_card()"><ut:locale_tag key="unlink.credit.card.submit"/></button>
</div>

</body>
</html>
