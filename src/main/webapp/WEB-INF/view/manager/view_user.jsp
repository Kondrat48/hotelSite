<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04-Sep-18
  Time: 04:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="user_title"/></title>
    </fmt:bundle>
    <link rel="stylesheet" href="../../../css/table.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<c:import url="../menu.jsp"/>
<div class="container">
    <fmt:bundle basename="page_strings" prefix="string.">
        <table>
            <tr>
                <td>
                    <fmt:message key="username"/>:
                </td>
                <td>
                        ${user.username}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="name"/>:
                </td>
                <td>
                        ${user.name}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="surname"/>:
                </td>
                <td>
                        ${user.surname}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="phone_number"/>:
                </td>
                <td>
                        ${user.phoneNumber}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="email"/>:
                </td>
                <td>
                        ${user.email}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="money"/>:
                </td>
                <td>
                        ${user.money}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="role"/>:
                </td>
                <td>
                    <fmt:message key="role.${fn:toLowerCase(user.role)}"/>
                </td>
            </tr>
        </table>
        <c:if test="${sessionScope.role=='admin'}">
            <a href="<c:url value="/app/edit_user_page?username=${user.username}"/>"
               class="btn btn-warning"><fmt:message key="edit"/></a>
            <a href="<c:url value="/app/delete_user?username=${user.username}"/>" class="btn btn-danger"><fmt:message
                    key="delete"/></a>
        </c:if>
    </fmt:bundle>
</div>
</body>
</html>
