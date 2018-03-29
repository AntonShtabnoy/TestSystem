<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>">
</head>
<body class="login2background" onload='document.loginForm.username.focus();'>

<div id="login-box">
    <div class="container">
        <div class="col-lg-6 col-md-6 col-sm-8  loginbox">
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-6">
                    <img src="<c:url value="/resources/image/testsyst.png"/>" alt="Logo" class="logo">
                </div>
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6  ">
                    <span class="singtext">Sign in </span>
                </div>
            </div>

            <form name='loginForm'
                  action="<c:url value='/j_spring_security_check' />" method='POST'>
                <div class=" row loginbox_content ">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-user"></span>
                        </span>
                        <input class="form-control" type="text" name="username" placeholder="User name">
                    </div>
                    <br>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-lock"></span>
                        </span>
                        <input class="form-control" type="password" name="password" placeholder="Password">
                    </div>
                </div>
                <div class="row ">
                    <div class="col-lg-8 col-md-8  col-sm-8 col-xs-7 forgotpassword ">
                        <a href="#"> Forgot Username / Password?</a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4  col-xs-5 ">
                        <input type="submit" name="submit" value="Submit" class=" btn btn-default submit-btn"/>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-4 "></div>
    </div>
</div>
</body>
</html>