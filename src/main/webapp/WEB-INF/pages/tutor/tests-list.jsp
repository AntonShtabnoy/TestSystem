<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Topics</title>
    <%@ include file="../header.jsp" %>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<h3>Tests</h3>
<div id="tests_table_container">
    <table id="tests_table" class="table table-striped table-bordered" style="width:100%">
        <thread>
            <tr>
                <th><input type="checkbox" id="checkAll"></th>
                <th>Name</th>
                <th>Description</th>
                <th>Topic</th>
            </tr>
        </thread>
        <c:forEach var="test" items="${tests}">
        <tbody>
        <tr>
            <th><input type="checkbox" value="${test.id}" name="topics-check"></th>
            <th><a href="<c:url value="/tutor/tests/${test.id}"/>">${test.name}</a></th>
            <th>${test.description}</th>
            <th>${test.topic.description}</th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<button name="add-test" type="button" class="btn btn-primary" onclick="window.location.href='/tutor/tests/create'">Add</button>
<button id="delete-test" type="button" class="btn btn-primary">Delete</button>


<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/tutor/tests-list.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>