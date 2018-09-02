<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17-Aug-18
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="../../../css/login.css"/>"/>
    <link rel="stylesheet" href="<c:url value="../../../css/bootstrap.css"/>"/>
    <meta charset="utf-8"/>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"--%>
          <%--integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>


    <title>Login</title>
</head>
<body>
<jsp:include page="../../../view/menu.jsp"/>
<div class="container">
    <fmt:bundle basename="page_strings" prefix="string.">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h2><fmt:message key="singin"/></h2>

                <form id="Login" class="form-username" role="form" method="post"
                      action="${pageContext.request.contextPath}/app/login">
                    <div class="form-group">
                        <input type="text" name="username" class="form-control"
                               placeholder="<fmt:message key="username"/>"
                               value="" required autofocus>
                            ${wrongusername}
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control"
                               placeholder="<fmt:message key="password"/>" value="" required>
                            ${wrongpassword}
                            ${errorLogin}
                    </div>

                    <input class="btn btn-primary" type="submit" value="<fmt:message key="login"/> ">
                </form>
            </div>
        </div>
    </div>
    </fmt:bundle>
</div>


</body>
</html>