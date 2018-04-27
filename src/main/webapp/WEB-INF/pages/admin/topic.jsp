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
<div id="topics_table_container">
    <table id="topics_table" class="table table-striped table-bordered" style="width:100%">
        <thread>
            <tr>
                <th><input type="checkbox" id="checkAll"></th>
                <th>Name</th>
                <th>Description</th>
            </tr>
        </thread>
        <c:forEach var="topic" items="${topics}">
        <tbody>
        <tr>
            <th><input type="checkbox" value="${topic.id}" name="topics-check"></th>
            <th><a href="<c:url value="/admin/topics/${topic.id}"/>">${topic.name}</a></th>
            <th>${topic.description}</th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--/admin/topics/create--%>
<button name="add-topic" type="button" class="btn btn-primary"
        onclick="window.location.href=window.location.pathname+'/create'">Add
</button>
<button id="delete-topic" type="button" class="btn btn-primary">Delete</button>


<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/admin/topic.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>