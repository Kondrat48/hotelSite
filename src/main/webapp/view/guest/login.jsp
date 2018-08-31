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

</body>
</html>
