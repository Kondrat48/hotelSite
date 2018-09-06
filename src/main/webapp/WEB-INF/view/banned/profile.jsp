<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26-Aug-18
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="profile_title"></fmt:message></title>
    </fmt:bundle>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../menu.jsp"/>
<div class="container">
    <div class="col col-md-8 col-lg-6 offset-md-2 offset-lg-3">
        <fmt:bundle basename="page_strings" prefix="string.">
            <form class="form-login" role="form" method="post" action="${pageContext.request.contextPath}/app/profile_update">
                <h2 class="form-heading"><fmt:message key="profile"/> </h2>
                <input type="hidden" name="old_email" value="${email}"/>
                <table class="w-100">
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
                            <fmt:message key="old_password"/>:
                        </td>
                        <td>
                            <input type="text" name="old_password" class="form-control" value="" required>
                                ${wrongpassword}
                                ${errorold_password}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="new_password"/>:
                        </td>
                        <td>
                            <input type="text" name="new_password" class="form-control" value="" required>
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
                            <fmt:message key="role.${role}"/>
                        </td>
                    </tr>
                </table>
                <br>
                <input class="btn btn-primary w-100 col col-md-6 offset-md-3" type="submit" value="<fmt:message key="update"/>">
            </form>
        </fmt:bundle>
    </div>
</div>
</body>
</html>
