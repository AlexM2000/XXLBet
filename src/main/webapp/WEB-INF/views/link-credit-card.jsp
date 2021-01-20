<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<html>
<head>
    <title><ut:locale_tag key="link.credit.card.title"/></title>
    <script src="${pageContext.request.contextPath}/js/link-credit-card.js"></script>
</head>
<body>
<div class="container my-5">
    <h3 class="text-info"><ut:locale_tag key="link.credit.card.title.form"/></h3>
    <div class="form-group">
        <input hidden id="userId" value="${sessionScope.user_id}"/>
        <div class="form-group">
            <label for="number"><ut:locale_tag key="link.credit.card.enter.number"/></label>
            <input type="text" class="form-control" id="number"/>
            <small class="text-info"><ut:locale_tag key="link.credit.card.number.format"/></small>
            <small class="text-danger" id="numberInformer"></small>
        </div>
        <div class="form-group">
            <label for="thru"><ut:locale_tag key="link.credit.card.enter.thru"/></label>
            <input type="text" class="form-control" id="thru"/>
            <small class="text-info"><ut:locale_tag key="link.credit.card.thru.format"/></small>
            <small class="text-danger" id="thruInformer"></small>
        </div>
        <div class="form-group">
            <label for="cvv"><ut:locale_tag key="link.credit.card.enter.cvv"/></label>
            <input type="text" id="cvv"/>
            <small class="text-info"><ut:locale_tag key="link.credit.card.cvv.format"/></small>
            <small class="text-danger" id="cvvInformer"></small>
        </div>
    </div>
    <button class="btn btn-primary" onclick="link_credit_card()"><ut:locale_tag key="link.credit.card.submit"/></button>
</div>
</body>
</html>
