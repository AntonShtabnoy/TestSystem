<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../header.jsp" %>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div class="col-xs-6">
    <h2 class="sub-header">Test statistics</h2>
    <div class="table-responsive" style="overflow-scrolling: auto">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">Test</th>
                <th class="col-md-2">Count attempts</th>
                <th class="col-md-3">Correct</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stat" items="${testStatistics}">
                <tr>
                    <td>${stat[0]}</td>
                    <td>${stat[1]}</td>
                    <td>${stat[2]}%</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="col-xs-6">
    <h2 class="sub-header">Question statistics</h2>
    <div class="table-responsive" style="overflow-scrolling: auto">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">Test</th>
                <th class="col-md-2">Question</th>
                <th class="col-md-3">Count attempts</th>
                <th class="col-md-3">Correct</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stat" items="${questionStatistics}">
                <tr>
                    <td>${stat[0]}</td>
                    <td>${stat[1]}</td>
                    <td>${stat[2]}</td>
                    <td>${stat[3]}%</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="col-xs-6">
    <h2 class="sub-header">Users statistics</h2>
    <div class="table-responsive" style="overflow-scrolling: auto">
        <table class="table table-striped">
            <thead>
            <tr>
                <th class="col-md-1">User</th>
                <th class="col-md-2">Test</th>
                <th class="col-md-3">Count attempts</th>
                <th class="col-md-3">Correct</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stat" items="${questionStatistics}">
                <tr>
                    <td>${stat[0]}</td>
                    <td>${stat[1]}</td>
                    <td>${stat[2]}</td>
                    <td>${stat[3]}%</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>