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
            <input type="text" name="email" class="form-control" id="InputEmail" placeholder=<ut:locale_tag key="registration.email"/> />
            <small class="text-danger" id="emailErrorInformer"></small>
        </div>
        <div class="form-group">
            <label for="InputLogin"><ut:locale_tag key="registration.phonenumber"/></label>
            <input type="text" name="phoneNumber" class="form-control" id="InputLogin" placeholder=<ut:locale_tag key="registration.phonenumber"/> />
            <small class="text-danger" id="phoneErrorInformer"></small>
        </div>
        <div class="form-group">
            <label for="InputSurName"><ut:locale_tag key="registration.surname"/></label>
            <input type="text" name="surname" id="InputSurName" placeholder=<ut:locale_tag key="registration.surname"/> />
        </div>
        <div class="form-group">
            <label for="InputName"><ut:locale_tag key="registration.name"/></label>
            <input type="text" name="name" id="InputName" placeholder=<ut:locale_tag key="registration.name"/> />
        </div>
        <div class="form-group">
            <label for="InputSecondName"><ut:locale_tag key="registration.secondname"/></label>
            <input type="text" name="secondName" id="InputSecondName" placeholder=<ut:locale_tag key="registration.secondname"/> />
        </div>
        <div class="form-group">
            <label for="InputPassword"><ut:locale_tag key="registration.password"/></label>
            <input type="password" name="password" class="form-control" id="InputPassword" placeholder=<ut:locale_tag key="registration.password"/> />
            <small class="text-info" id="passwordFormatInformer"><ut:locale_tag key="registration.password.format"/></small>
            <small class="text-danger password_field"></small>
        </div>
        <div class="form-group">
            <label for="InputRPassword"><ut:locale_tag key="registration.repeatpassword"/></label>
            <input type="password" name="repeatPassword" class="form-control" id="InputRPassword" placeholder=<ut:locale_tag key="registration.repeatpassword"/> />
            <small class="text-danger password_field"></small>
        </div>
        <div class="form-group">
            <label for="InputBirthDate"><ut:locale_tag key="registration.birthdate"/></label>
            <input type="date" name="birthDate" id="InputBirthDate"/>
        </div>
    </form>
    <button type="button" class="btn btn-primary" onclick="registration()"><ut:locale_tag key="registration.submit"/></button>
</div>
</body>
</html>
