<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30-Aug-18
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="user_management_title"/></title>
    </fmt:bundle>
    <link rel="stylesheet" href="../../../css/table.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<fmt:bundle basename="page_strings" prefix="string.">

    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="username"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="role"/></th>
            <th></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td class="align-middle">${user.id}</td>
                <td class="align-middle">${user.username}</td>
                <td class="align-middle">${user.name}</td>
                <td class="align-middle">${user.surname}</td>
                <td class="align-middle">${user.email}</td>
                <td class="align-middle"><fmt:message key="role.${fn:toLowerCase(user.role)}"/></td>
                <td class="align-middle"><a class="btn btn-info w-100"
                                            href="<c:url value="/app/view_user_page?username=${user.username}"/>"><fmt:message
                        key="info"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="../pagination_footer.jsp"/>
</fmt:bundle>

</body>
</html>
