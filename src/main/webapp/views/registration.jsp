<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@ taglib prefix="ut" uri="/WEB-INF/mytags" %>
<%@include file="/views/header.jsp"%>
</head>
<body>
<div class="container my-5">
    <form id="registration">
        <div class="form-group">
            <label for="InputEmail"><ut:locale_tag key="registration.email"/></label>
            <input type="text" name="email" class="form-control" id="InputEmail" placeholder=<ut:locale_tag key="registration.enter-email"/> />
            <small class="text-danger" id="emailErrorInformer"></small>
        </div>
        <div class="form-group">
            <label for="InputLogin"><ut:locale_tag key="registration.phone-number"/></label>
            <input type="text" name="login" class="form-control" id="InputLogin" placeholder=<ut:locale_tag key="registration.enter-phonenumber"/> />
            <small class="text-danger" id="phoneErrorInformer"></small>
        </div>
        <div class="form-group">
            <label for="InputSurName"><ut:locale_tag key="registration.surname"/></label>
            <input type="text" name="realName" id="InputSurName" placeholder=<ut:locale_tag key="registration.enter-surname"/> />
        </div>
        <div class="form-group">
            <label for="InputPassword"><ut:locale_tag key="registration.password"/></label>
            <input type="password" name="password" class="form-control" id="InputPassword" placeholder="Enter Password">
            <small class="text-info" id="passwordFormatInformer"><ut:locale_tag key="registration.password.format"/></small>
            <small class="text-danger password_field"></small>
        </div>
        <div class="form-group">
            <label for="InputRPassword"><ut:locale_tag key="registration.repeatpassword"/></label>
            <input type="password" name="rPassword" class="form-control" id="InputRPassword" placeholder="Repeat Password">
            <small class="text-danger password_field"></small>
        </div>
        <div class="form-group">
            <label for="InputRacketSetup"><ut:locale_tag key="registration.racketsetup"/></label>
            <input type="text" name="racketSetup" id="InputRacketSetup" placeholder="Enter your racket setup" />
        </div>
    </form>
    <button type="button" class="btn btn-primary" onclick="registration()"><ut:locale_tag key="registration.submit"/></button>
</div>
</body>
</html>
