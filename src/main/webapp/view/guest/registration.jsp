<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17-Aug-18
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<jsp:include page="../menu.jsp"/>

<fmt:bundle basename="page_strings" prefix="string.">
    <form class="form-login" role="form" method="post" action="${pageContext.request.contextPath}/app/register">
        <h2 class="form-heading"><fmt:message key="registration"/></h2>
        <table>
            <tr>
                <td>
                    <fmt:message key="username"/>:
                </td>
                <td>
                    <input type="text" name="username" class="form-control" value="${username}" required autofocus>
                        ${existusername}
                        ${wrongusername}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="password"/>:
                </td>
                <td>
                    <input type="text" name="password" class="form-control" value="${password}" required>
                        ${wrongpassword}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="name"/>:
                </td>
                <td>
                    <input type="text" name="name" class="form-control" value="${name}">
                        ${wrongname}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="surname"/>:
                </td>
                <td>
                    <input type="text" name="surname" class="form-control" value="${surname}">
                        ${wrongsurname}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="phone_number"/>:
                </td>
                <td>
                    <input type="text" name="phone_number" class="form-control" value="${phone_number}">
                        ${wrongphone_number}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="email"/>:
                </td>
                <td>
                    <input type="text" name="email" class="form-control" value="${email}" required>
                        ${existemail}
                        ${wrongemail}
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="<fmt:message key="register"/>">
        </form>
</fmt:bundle>

</body>
</html>
