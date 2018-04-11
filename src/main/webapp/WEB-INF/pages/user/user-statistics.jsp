<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css"/>">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<c:set var="count" value="${sessionScope.get('countQuestions')}"/>
<c:set var="wrong" value="${wrongQuestions.size()}"/>

<h1>Statistics</h1>
<h1>All questions: ${count}</h1>
<h1>Correct answers: ${count-wrong}</h1>
<h1>Wrong answers: ${wrong}</h1>
<h1>Correct: ${(count-wrong)/count*100}%</h1>
<div id="users_table_container">
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thread>
            <tr>
                <th>Wrong question</th>
                <th>Literature</th>
                <th>Links</th>
            </tr>
        </thread>
        <c:forEach var="question" items="${wrongQuestions}">
        <tbody>
        <tr>
            <th>${question.description}</th>
            <th>${question.literatureList.toArray()[0].description}</th>
            <th>${question.literatureList.toArray()[0].links.toArray()[0].link}</th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--<div class="paragraphs">--%>
<%--<div class="row">--%>
<%--<div class="span4">--%>
<%--<img style="float:left; margin-left: 25%" src="<c:url value="/resources/image/image1.png"/>"/>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>