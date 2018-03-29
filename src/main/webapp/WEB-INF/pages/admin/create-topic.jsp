<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/create-user.css"/>">
    <%@ include file="../header.jsp" %>
</head>
<body>

<c:choose>
    <c:when test="${empty topic.name}">
        <c:set var="formAction" value="/admin/topics/create" />
        <c:set var="button" value="Add" />
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/admin/topics/${topic.id}" />
        <c:set var="button" value="Edit"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <form:form method="post" action="${formAction}" modelAttribute="topic">
                <div class="form-group">
                    <form:label path="name" class="cols-sm-2 control-label">Topic</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <form:input path="name" class="form-control" name="name" id="name"  placeholder="Enter topic name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="description" class="cols-sm-2 control-label">Description</form:label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <form:textarea path="description" class="form-control" name="description" id="description"  placeholder="Enter topic description"/>
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
