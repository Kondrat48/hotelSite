<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17-Aug-18
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<h2>
    Error Page<br/>
</h2>
<br>
<fmt:setBundle basename="path" var="path"/>
<a href="${pageContext.request.contextPath}<fmt:message key="path.page.index" bundle="${path}"/>">Index</a>


</body>
</html>