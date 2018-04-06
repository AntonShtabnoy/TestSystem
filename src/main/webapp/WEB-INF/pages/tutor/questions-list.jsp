<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Topics</title>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-select.min.css"/>"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<select id="pickerId" class="selectpicker" data-style="btn-info" data-live-search="true">
    <c:forEach var="test" items="${tests}">
        <option value="${test.id}">${test.name}</option>
    </c:forEach>
</select>
<div id="questions_table_container">
    <table id="questions_table" class="table table-striped table-bordered" style="width:100%">
        <thread>
            <tr>
                <th><input type="checkbox" id="checkAll"></th>
                <th>Description</th>
                <th>Answers</th>
            </tr>
        </thread>
        <c:forEach var="question" items="${questions}">
        <tbody>
        <tr>
            <th><input type="checkbox" value="${question.id}" name="topics-check"></th>
            <th><a href="<c:url value="/tutor/questions/${question.id}"/>">${question.description}</a></th>
            <th>${question.description}</th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<button name="add-question" type="button" class="btn btn-primary"
        onclick="addURL()">Add
</button>
<button id="delete-question" type="button" class="btn btn-primary">Delete</button>


<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-select.min.js"/>"></script>
<script src="<c:url value="/resources/js/tutor/tutor-questions.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>