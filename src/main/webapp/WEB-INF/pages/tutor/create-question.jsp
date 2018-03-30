<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/create-user.css"/>">
    <%@ include file="../header.jsp" %>
</head>
<body>

<c:choose>
    <c:when test="${empty test.name}">
        <c:set var="formAction" value="/tutor/tests/create"/>
        <c:set var="button" value="Add"/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/tutor/tests/${test.id}"/>
        <c:set var="button" value="Edit"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <form method="post" action="${formAction}" modelAttribute="test">
                <div class="form-group">
                    <label for="description" class="cols-sm-2 control-label">Question</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" id="description">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="type" class="cols-sm-2 control-label">Type of question</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <select id="type" class="form-control">
                                <option value="single">Single correct answer</option>
                                <option value="multiple">Multiple correct answer</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <input type="radio" id="radio1" aria-label="Radio button for following text input">
                                </div>
                            </div>
                            <input type="text" id="answer1" class="form-control"
                                   aria-label="Text input with radio button">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text">
                                    <input type="radio" id="radio2" aria-label="Radio button for following text input">
                                </div>
                            </div>
                            <input type="text" id="answer2" class="form-control"
                                   aria-label="Text input with radio button">
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="${button}">
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/create-question.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>
