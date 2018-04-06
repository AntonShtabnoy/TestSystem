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

<h1>Statistics</h1>
<div class="paragraphs">
    <div class="row">
        <div class="span4">
            <img style="float:left; margin-left: 25%" src="<c:url value="/resources/image/image1.png"/>"/>
        </div>
    </div>
</div>


<script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>