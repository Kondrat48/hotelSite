<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26-Aug-18
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<h2>
    Access error
</h2>

<fmt:setBundle basename="path" var="path"/>
<a href="${pageContext.request.contextPath}<fmt:message key="path.page.index" bundle="${path}"/>">Index</a>


</body>
</html>