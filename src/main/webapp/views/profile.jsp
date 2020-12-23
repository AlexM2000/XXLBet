
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><ut:locale_tag key="profile.title"/></title>
</head>
<body>
<%@include file="/views/header.jsp"%>
<div class="container">
    <div class="row m-5">
        <div class="col-3 bg-light">
            <jstl:choose>
            <jstl:when test="${not empty userInfo.getProfileImgPath()}">
                <img src="data:${user.getImage().getImageType()};base64,${user.getImage().getImageData()}" class="w-50 float-left m-2">
                <button id="DeleteImage" class="btn-sm btn-info" onclick="delete_image(${user.getImage().getId()})"><ut:locale_tag key="profile.delete.image"/></button>
            </jstl:when>
            <c:otherwise>
                <img id="profImg" src="images/img_311846.png" class="w-50 float-left m-2">
            </c:otherwise>
            </jstl:choose>
            <p id="ProfileImageDiv" class="float-right mt-5">${user.getUserInfo().getRealName()}</p>
            <form id="ProfileImageForm" action="/profileimage" style="display: none" method="post" enctype="multipart/form-data">
                <input type="file" name="file" id="file" class="btn-sm btn-info" value="Choose image"/>
                <button type="submit" id="UploadProfileImage" style="display: none" class="btn-sm btn-info">Upload</button>
            </form>
            <button id="CancelUploadProfileImage" style="display: none" onclick="cancel_display_upload_image()" class="btn-sm btn-info">Cancel</button>
            <button id="DisplayUploadImage" class="btn-sm btn-info" onclick="display_upload_image()">Change Image</button>
        </div>
        <div class="col-9">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Topic</th>
                    <th scope="col">Description</th>
                    <th scope="col">Date issued</th>
                </tr>
                </thead>
                <tbody>
                <jstl:forEach var="article" items="${articles}">
                <form id="articleInfo-${article.getId()}">
                    <tr id="tr-${article.getId()}">
                        <input type="hidden" name="id" value="${article.getId()}"/>
                        <td name="topic"> <a href="articles/${article.getId()}"> ${article.getTopic()} </a></td>
                        <td name="content"> ${article.getContent()} </td>
                        <td> ${article.getDateIssued()} </td>
                        <td><button type="button" class="btn btn-dark"
                                    onclick="delete_article(${article.getId()})">Delete</button></td>
                    </tr>
                </form>
                </jstl:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <form div class="m-5" id="newArticleForm" enctype="multipart/form-data" action="#" method="post">
        <p><b>Topic</b></p>
        <input type="text" class="form-control mb-3 mr-5 pr-5" id="topic" name="topic" placeholder="Enter the topic...">
        <small class="text-danger" id="TopicErrorInformer"></small>
        <p><b>New Post:</b></p>
        <p id="articleP"><textarea class="form-text" rows="8" cols="140" id="article" name="article" placeholder="Write your text here..."></textarea></p>
        <small class="text-danger" id="textAreaErrorInformer"></small>
        <input style="display: none" type="file" multiple="multiple" name="file" id="file_into_textarea" class="btn-sm btn-info" value="Choose image"/>
        <small class="text-danger" id="fileErrorInformer"></small>
    </form>
    <button style="display: none" id="insert_image_button" type="button" class="btn-sm btn-info" onclick="insert_image_into_textarea()">Upload</button>
    <button style="display: none" id="cancel_insert_image_button" type="button" class="btn-sm btn-info" onclick="cancel_show_button__img_into_textarea()">Cancel</button>
    <button id="show_insert_image" onclick="show_button__img_into_textarea()" type="button" class="btn-sm btn-info">Insert image</button>
    <button type="button" onclick="insert_article()" class="my-3 btn btn-primary w-25 align-items-lg-center">Post</button>
</div>
<%@include file="/views/include/Footer.jsp"%>
</body>
</html>