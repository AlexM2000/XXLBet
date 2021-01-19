<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="/WEB-INF/views/header.jsp"%>
<head>
    <title><ut:locale_tag key="profile.title"/></title>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
</head>
<body>
<div class="container">
    <div class="row m-5">
        <div class="col-3 bg-light">
                <img id="profImg" src="${pageContext.request.contextPath}/images/img_311846.png" class="w-50 float-left m-2">
            <p id="ProfileImageDiv" class="float-right mt-5">${requestScope.userInfo.getSurname()} ${requestScope.userInfo.getName()} ${requestScope.userInfo.getSecondName()}</p>
        </div>
        <div class="col-5 float-right mt-4">
            <p><ut:locale_tag key="profile.birthdate"/>: ${requestScope.userInfo.getBirthDate()}</p>
            <p><ut:locale_tag key="profile.registration-date"/>: ${ut:formatLocalDateTime(requestScope.userInfo.getRegistrationDate(), 'dd.MM.yyyy HH:mm:ss')}</p>
            <p><ut:locale_tag key="profile.balance"/>: ${requestScope.userInfo.getBalance()}</p>
            <jstl:if test="${cards.size() > 0}">
            <div id="balanceForm" class="form-group">
                <small style="display: none" id="moneyLabel"><ut:locale_tag key="profile.enter.sum"/></small>
                <input style="display: none" id="money" type="number" class="mt-2" placeholder=<ut:locale_tag key="profile.enter.sum"/>/>
                <small class="form-control-sm" style="display: none" id="chooseCardLabel"><ut:locale_tag key="profile.choose.card"/></small>
                    <select style="display: none" id="chooseCard" class="form-control-sm">
                        <jstl:forEach var="card" items="${cards}">
                            <option value="${card.getNumber()}">${card.getNumber()} - ${card.getThru()}</option>
                        </jstl:forEach>
                    </select>
                    <a class="form-control text-info" href="${pageContext.request.contextPath}/xxlbet?command=create_credit_card_page"><ut:locale_tag key="profile.add.more.credit.cards"/></a>
                    <a class="form-control text-info" href="${pageContext.request.contextPath}/xxlbet?command=remove_credit_card_page"><ut:locale_tag key="profile.remove.credit.card"/></a>
                    <button style="display: none" id="payButton" class="btn-sm btn-info"><ut:locale_tag key="profile.pay.button"/></button>
                <button id="showPay" onclick="showPayForm()" class="btn-sm btn-info"><ut:locale_tag key="profile.show.pay"/></button>
                </jstl:if>
                <jstl:if test="${cards.size() == 0}">
                    <div>
                        <h6 id="noCreditCards" class="text-info"><ut:locale_tag key="profile.no.credit.cards"/></h6>
                    </div>
                    <div>
                        <a class="text-info" href="${pageContext.request.contextPath}/xxlbet?command=create_credit_card_page"><ut:locale_tag key="profile.add.credit.card"/></a>
                    </div>
                </jstl:if>
                <p><ut:locale_tag key="profile.role"/>: ${sessionScope.role.getName()}</p>
                <p><ut:locale_tag key="profile.status"/>: ${sessionScope.status.getName()}</p>
            </div>
        </div>
        <select id="betSelector" class="form-control form-control-lg" title=<ut:locale_tag key="profile.bets.title"/>>
            <option value="win_user_bets"><ut:locale_tag key="profile.bets.win"/></option>
            <option value="defeat_user_bets"><ut:locale_tag key="profile.bets.defeat"/></option>
            <option value="all_user_bets" selected><ut:locale_tag key="profile.bets.all"/></option>
        </select>
        <div class="col-9">
            <table id="betsTable" class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><ut:locale_tag key="profile.match"/></th>
                    <th scope="col"><ut:locale_tag key="profile.sum"/></th>
                    <th scope="col"><ut:locale_tag key="profile.coefficient"/></th>
                    <th scope="col"><ut:locale_tag key="profile.winning.sum"/></th>
                </tr>
                </thead>
                <tbody id="betsBody">
                </tbody>
            </table>
            <div id="pagesNums">

            </div>
        </div>
    </div>
</div>
</body>
</html>