<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/create-user.css"/>">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <%@ include file="../header.jsp" %>
</head>
<body>

<c:choose>
    <c:when test="${empty test.name}">
        <c:set var="formAction" value="/tutor/tests/create"/>
        <c:set var="button" value="Add question"/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="/tutor/tests/${test.id}"/>
        <c:set var="button" value="Edit"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <form method="post" action="${formAction}" id="formId">
                <div class="form-group">
                    <label for="description" class="cols-sm-2 control-label">Question</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <textarea rows="2" class="form-control" id="description"> </textarea>
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
                <label for="type" class="cols-sm-2 control-label">Answers</label>
                <div class="form-group">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="radio" value="0" name="radio-group"
                                           aria-label="Checkbox for following text input">
                                </span>
                                <textarea rows="2" name="text_answer" class="form-control"
                                          aria-label="Text input with checkbox"></textarea>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="radio" value="1" name="radio-group"
                                           aria-label="Checkbox for following text input">
                                </span>
                                <textarea rows="2" name="text_answer" class="form-control"
                                          aria-label="Text input with checkbox"></textarea>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="radio" name="radio-group" value="2"
                                           aria-label="Checkbox for following text input">
                                </span>
                                <textarea rows="2" name="text_answer" class="form-control"
                                          aria-label="Text input with checkbox"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <button id="add_answer" style="color: #229eae">Add answer</button>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-12">
                            <label class="cols-sm-2 control-label">Literature</label>
                            <textarea id="literature" rows="1" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <label class="cols-sm-2 control-label">Links</label>
                            <textarea id="link" rows="1" class="form-control"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group ">
                    <div class="row">
                        <div class="col-md-6">
                            <input type="button" class="btn btn-primary btn-lg btn-block login-button" id="finishId"
                                   value="Finish">
                        </div>
                        <div class="col-md-6">
                            <input type="button" class="btn btn-primary btn-lg btn-block login-button" id="nextId"
                                   value="${button}">
                        </div>
                    </div>
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
