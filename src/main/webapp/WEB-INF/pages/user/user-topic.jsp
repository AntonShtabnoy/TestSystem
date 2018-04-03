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

<div class="paragraphs">
    <div class="row">
        <div class="span4">
            <img style="float:left; margin-left: 25%" src="<c:url value="/resources/image/image1.png"/>"/>
        </div>
        <form method="post" action="/user/tests" id="formId">
            <div class="user-main container">
                <select class="selectpicker" name="topic" data-style="btn-info" data-live-search="true">
                    <c:forEach var="topic" items="${topics}">
                        <option value="${topic.id}">${topic.name}</option>
                    </c:forEach>
                </select>
                <div class="row">
                    <select class="selectpicker" name="test" data-style="btn-info" data-live-search="true">
                        <c:forEach var="test" items="${tests}">
                            <option value="${test.id}">${test.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row">
                    <input type="submit" class="btn btn-primary btn-lg btn-block login-button" id="runId"
                           value="Start test">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</div>


<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-select.min.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>