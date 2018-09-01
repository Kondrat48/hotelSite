<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 31-Aug-18
  Time: 06:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<jsp:include page="../menu.jsp"/>


<fmt:bundle basename="page_strings" prefix="string.">
    <form class="form-login" role="form" method="post" action="${pageContext.request.contextPath}/app/edit_user">
        <h2 class="form-heading"><fmt:message key="user_editing"/></h2>
        <c:set var="old_username" value="${username}"/>
        <c:set var="old_email" value="${email}"/>
        <input type="hidden" name="old_username" value="${username}">
        <input type="hidden" name="old_email" value="${email}">
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
            <tr>
                <td>
                    <fmt:message key="money"/>:
                </td>
                <td>
                    <input type="text" name="money" class="form-control" value="${money}" required>
                        ${wrongmoney}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="role"/>:
                </td>
                <td>
                    <select name="role">
                        <option value="user" ${role == 'user'?'selected':''}><fmt:message key="role.user"/></option>
                        <option value="banned" ${role == 'banned'?'selected':''}><fmt:message key="role.banned"/></option>
                        <option value="manager" ${role == 'manager'?'selected':''}><fmt:message key="role.manager"/></option>
                        <option value="admin" ${role == 'admin'?'selected':''}><fmt:message key="role.admin"/></option>
                    </select>
                        ${wrongrole}
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="<fmt:message key="update"/>">
    </form>
</fmt:bundle>


</body>
</html>
