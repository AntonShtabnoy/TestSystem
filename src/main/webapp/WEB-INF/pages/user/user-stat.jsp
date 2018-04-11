<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page session="true" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user-stat.css"/>">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div id="users_table_container">
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thread>
            <tr>
                <th>Test</th>
                <th>Question</th>
                <th>Count attempts</th>
                <th>Correct</th>
            </tr>
        </thread>
        <c:forEach var="stat" items="${statistics}">
        <tbody>
        <tr>
            <th>${stat[0]}</th>
            <th>${stat[1]}</th>
            <th>${stat[2]}</th>
            <th>${stat[3]}%</th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>