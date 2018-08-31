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
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


    <title>Login</title>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<fmt:bundle basename="page_strings" prefix="string.">
    <form class="form-username" role="form" method="post" action="${pageContext.request.contextPath}/app/login">
        <h2 class="form-heading"><fmt:message key="singin"/></h2>
        <input type="text" name="username" class="form-control" placeholder="<fmt:message key="username"/>" value="" required autofocus>
        ${wrongusername}
        <input type="password" name="password" class="form-control" placeholder="<fmt:message key="password"/>" value="" required>
        ${wrongpassword}
        ${errorLogin}
        <input type="submit" value="<fmt:message key="login"/> ">
    </form>
</fmt:bundle>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
