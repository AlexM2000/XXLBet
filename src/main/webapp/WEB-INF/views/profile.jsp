
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
            <jstl:choose>
            <jstl:when test="${not empty requestScope.userInfo.getProfileImgPath()}">
                <img src="${requestScope.userInfo.getProfileImgPath()}" class="w-50 float-left m-2">
                <button id="DeleteImage" class="btn-sm btn-info" onclick="delete_image()"><ut:locale_tag key="profile.delete.image"/></button>
            </jstl:when>
            <c:otherwise>
                <img id="profImg" src="/images/img_311846.png" class="w-50 float-left m-2">
            </c:otherwise>
            </jstl:choose>
            <p id="ProfileImageDiv" class="float-right mt-5">${requestScope.userInfo.getSurname()} ${requestScope.userInfo.getName()} ${userInfo.getSecondName()}</p>
            <form id="ProfileImageForm" action="/profileimage" style="display: none" method="post" enctype="multipart/form-data">
                <input type="file" name="file" id="file" class="btn-sm btn-info" value="Choose image"/>
                <button type="submit" id="UploadProfileImage" style="display: none" class="btn-sm btn-info">Upload</button>
            </form>
            <button id="CancelUploadProfileImage" style="display: none" onclick="cancel_display_upload_image()" class="btn-sm btn-info">Cancel</button>
            <button id="DisplayUploadImage" class="btn-sm btn-info" onclick="display_upload_image()">Change Image</button>
        </div>
        <select id="betSelector" class="form-control form-control-lg" title=<ut:locale_tag key="profile.bets.title"/>>
            <option value="win_user_bets"><ut:locale_tag key="profile.bets.win"/></option>
            <option value="defeat_user_bets"><ut:locale_tag key="profile.bets.defeat"/></option>
            <option value="all_user_bets"><ut:locale_tag key="profile.bets.all"/></option>
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