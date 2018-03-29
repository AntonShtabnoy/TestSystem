<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Title</title>
    <%@ include file="../header.jsp" %>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </head>
<body>
    <div id="users_table_container">
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thread>
        <tr>
            <th><input type="checkbox" id="checkAll"></th>
            <th>Name</th>
            <th>login</th>
            <th>Role</th>
        </tr>
        </thread>
        <c:forEach var="user" items="${users}">
        <tbody>
        <tr>
            <th><input type="checkbox" value="${user.id}" name="users-check"></th>
        <th><a href="<c:url value="/admin/${user.id}"/>">${user.firstName} ${user.lastName}</a></th>
            <th>${user.login}</th>
            <th>${user.role.name}</th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <button name="add-user" type="button" class="btn btn-primary" onclick="window.location.href='/admin/create'">Add</button>
    <button id="delete-user" type="button" class="btn btn-primary">Delete</button>

    <%--<table id="grid-selection" class="table table-condensed table-hover table-striped">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th data-column-id="id" data-type="numeric" data-identifier="true">ID</th>--%>
            <%--<th data-column-id="sender">Sender</th>--%>
            <%--<th data-column-id="received" data-order="desc">Received</th>--%>
            <%--<th data-column-id="link" data-formatter="link" data-sortable="false">Link</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
    <%--</table>--%>
    <%--<script>--%>
        <%--$("#grid-selection").bootgrid({--%>
            <%--ajax: true,--%>
            <%--post: function ()--%>
            <%--{--%>
                <%--/* To accumulate custom parameter with the request object */--%>
                <%--return {--%>
                    <%--id: "b0df282a-0d67-40e5-8558-c9e93b7befed"--%>
                <%--};--%>
            <%--},--%>
            <%--url: "/api/data/basic",--%>
            <%--selection: true,--%>
            <%--multiSelect: true,--%>
            <%--formatters: {--%>
                <%--"link": function(column, row)--%>
                <%--{--%>
                    <%--return "<a href=\"#\">" + column.id + ": " + row.id + "</a>";--%>
                <%--}--%>
            <%--}--%>
        <%--}).on("selected.rs.jquery.bootgrid", function(e, rows)--%>
        <%--{--%>
            <%--var rowIds = [];--%>
            <%--for (var i = 0; i < rows.length; i++)--%>
            <%--{--%>
                <%--rowIds.push(rows[i].id);--%>
            <%--}--%>
            <%--alert("Select: " + rowIds.join(","));--%>
        <%--}).on("deselected.rs.jquery.bootgrid", function(e, rows)--%>
        <%--{--%>
            <%--var rowIds = [];--%>
            <%--for (var i = 0; i < rows.length; i++)--%>
            <%--{--%>
                <%--rowIds.push(rows[i].id);--%>
            <%--}--%>
            <%--alert("Deselect: " + rowIds.join(","));--%>
        <%--});--%>
    <%--</script>--%>
	<%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
		<%--<h2>--%>
			<%--Welcome : ${pageContext.request.userPrincipal.name} | <a--%>
				<%--href="javascript:formSubmit()"> Logout</a>--%>
		<%--</h2>--%>
	<%--</c:if>--%>
    <script src="<c:url value="/resources/js/jquery-3.3.1.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
    <script src="<c:url value="/resources/js/admin.js"/>"></script>
    <script src="<c:url value="/resources/js/header.js"/>"></script>
</body>
</html>