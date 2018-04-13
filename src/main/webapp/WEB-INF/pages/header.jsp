<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <c:choose>
        <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <c:set var="nav_li_2" value="Topic"/>
            <c:set var="nav_li_3" value="Statistics"/>
            <c:set var="nav_link_1" value="/admin"/>
            <c:set var="nav_link_2" value="/admin/topics"/>
            <c:set var="nav_link_3" value="/admin/statistics"/>
        </c:when>
        <c:when test="${pageContext.request.isUserInRole('ROLE_TUTOR')}">
            <c:set var="nav_li_2" value="Questions"/>
            <c:set var="nav_li_3" value="Statistics"/>
            <c:set var="nav_link_1" value="/tutor/"/>
            <c:set var="nav_link_2" value="/tutor/questions/0"/>
            <c:set var="nav_link_3" value="/tutor/statistics"/>
        </c:when>
        <c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
            <c:set var="nav_li_2" value="Topic"/>
            <c:set var="nav_link_1" value="/user/"/>
            <c:set var="nav_link_2" value="/user/topics"/>
        </c:when>
    </c:choose>
    <nav class="navbar navbar-default">
        <a href="<c:url value="${nav_link_1}"/>" class="navbar-left"><img
                src="<c:url value="/resources/image/testsyst.png"/>"></a>
        <ul class="nav navbar-nav navbar-left">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="${nav_link_1}"/>">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="${nav_link_2}"/>">${nav_li_2}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="${nav_link_3}"/>">${nav_li_3}</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Welcome,${pageContext.request.userPrincipal.name}<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="javascript:this.formSubmit()">Logout</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</head>
<body>

<c:url value="/j_spring_security_logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
</body>
</html>
