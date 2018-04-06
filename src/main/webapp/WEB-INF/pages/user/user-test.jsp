<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-select.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<input type="hidden" id="count" value="${countQuestions}">
<div class="paragraphs">
    <div class="row">
        <div class="span4">
            <img style="float:left; margin-left: 25%" src="<c:url value="/resources/image/image1.png"/>"/>
        </div>
        <div class="user-main container" id="mainId">
            <input type="hidden" id="questionId" value="${question.id}">
            <div class="form-group">
                <label for="description" class="cols-sm-2 control-label">Question</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <textarea rows="2" class="form-control" id="description">${question.description}</textarea>
                    </div>
                </div>
            </div>
            <label class="cols-sm-2 control-label">Answers</label>
            <div class="form-group">
                <div class="row">
                    <div id="answersId" class="col-lg-12">
                        <c:forEach var="answer" items="${question.answers}">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="${type}" value="${answer.id}" name="radio-group"
                                           aria-label="Checkbox for following text input">
                                </span>
                                <textarea rows="2" name="text_answer" class="form-control"
                                          aria-label="Text input with checkbox">${answer.description}</textarea>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <form method="post" action="/user/t" id="formId">
                <div class="form-group ">
                    <div class="row">
                        <div class="col-md-6">
                            <input type="button" class="btn btn-primary btn-lg btn-block login-button" id="finishId"
                                   value="Finish">
                        </div>
                        <div class="col-md-6">
                            <input type="button" class="btn btn-primary btn-lg btn-block login-button" id="nextId"
                                   value="Next">
                        </div>
                    </div>
                    <input type="hidden" name="resultMap" id="resultMapId">
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-select.min.js"/>"></script>
<script src="<c:url value="/resources/js/user/user-test.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>