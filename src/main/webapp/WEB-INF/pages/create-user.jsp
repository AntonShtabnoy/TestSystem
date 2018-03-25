<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/create-user.css"/>">
    <%@ include file="header.jsp" %>
</head>
<body>

<c:choose>
    <c:when test="${empty user.login}">
        <c:set var="formAction" value="/admin/create" />
        <c:set var="password" value="Enter your password" />
        <c:set var="button" value="Register" />
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/admin/${user.id}" />
        <c:set var="password" value="Enter new password" />
        <c:set var="button" value="Edit"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">
                <form:form method="post" action="${formAction}" modelAttribute="user">
                <div class="form-group">
                    <form:label path="firstName" class="cols-sm-2 control-label">Your Name</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <form:input path="firstName" class="form-control" name="firstName" id="firstName"  placeholder="Enter your Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="lastName" class="cols-sm-2 control-label">Your Surname</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <form:input path="lastName" class="form-control" name="lastName" id="lastName"  placeholder="Enter your Last name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="login" class="cols-sm-2 control-label">Username</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <form:input path="login" class="form-control" name="login" id="login"  placeholder="Enter your Username"/>
                        </div>
                    </div>
                </div>
                    <c:if test="${empty user.login}">
                    <div class="form-group">
                    <form:label path="password" class="cols-sm-2 control-label">Password</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <form:input type="password" path="password" class="form-control" name="password" id="password"  placeholder="${password}"/>
                        </div>
                    </div>
                </div>
                    </c:if>
                <div class="form-group">
                    <form:label path="role.name" class="cols-sm-2 control-label">Role</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-group fa-lg" aria-hidden="true"></i></span>
                            <form:input path="role.name" class="form-control" name="role" id="role"  placeholder="Enter your role"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="${button}">
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form:form>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>
