<%@ page import="java.util.Locale" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28-Aug-18
  Time: 09:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<c:set var="language"
       value="${not empty param.language ?
       param.language : not empty language ?
       language: pageContext.request.locale.language=='uk' ?
       'uk_UA':'en_US'}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>


<form <%--onsubmit="location.reload(true)"--%> action="${requestScope.javax.servlet.forward.request_uri}">
    <select id="language" name="language" onchange="submit()">
        <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select>
</form>
<fmt:bundle basename="page_strings" prefix="string.">
    <table>
        <tr>
            <td>
                <fmt:message key="role"/>:
            </td>
            <td>
                <fmt:message key="role.${sessionScope.role}"/>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="username"/>:
            </td>
            <td>
                    ${sessionScope.username}
            </td>
        </tr>
    </table><br>
</fmt:bundle>
<fmt:bundle basename="menu_items" prefix="item.">
    <a href="/app/default"><fmt:message key="main"/></a> ||
    <c:if test="${sessionScope.role=='guest'}">
        <a href="/app/login_page"><fmt:message key="login"/></a> ||
        <a href="/app/register_page"><fmt:message key="register"/></a>
    </c:if>
    <c:if test="${sessionScope.role=='banned'}">
        <a href="/app/profile_page"><fmt:message key="profile"/></a> ||
        <a href="/app/logout"><fmt:message key="logout"/></a>
    </c:if>
    <c:if test="${sessionScope.role=='user'}">
        <a href="/app/booking_page"><fmt:message key="booking"/></a> ||
        <a href="/app/profile_page"><fmt:message key="profile"/></a> ||
        <a href="/app/logout"><fmt:message key="logout"/></a>
    </c:if>
    <c:if test="${sessionScope.role=='manager'}">
        <a href="/app/booking_page"><fmt:message key="booking"/></a> ||
        <a href="/app/profile_page"><fmt:message key="profile"/></a> ||
        <a href="/app/book_management_page"><fmt:message key="book_management"/></a> ||
        <a href="/app/room_management_page"><fmt:message key="room_management"/></a> ||
        <a href="/app/room_confirmation_page"><fmt:message key="confirmation_management"/></a> ||
        <a href="/app/logout"><fmt:message key="logout"/></a>
    </c:if>
    <c:if test="${sessionScope.role=='admin'}">
        <a href="/app/booking_page"><fmt:message key="booking"/></a> ||
        <a href="/app/profile_page"><fmt:message key="profile"/></a> ||
        <a href="/app/book_management_page"><fmt:message key="book_management"/></a> ||
        <a href="/app/room_management_page"><fmt:message key="room_management"/></a> ||
        <a href="/app/confirmation_page"><fmt:message key="confirmation_management"/></a> ||
        <a href="/app/user_management_page"><fmt:message key="user_management"/></a> ||
        <a href="/app/session_management_page"><fmt:message key="session_management"/></a> ||
        <a href="/app/logout"><fmt:message key="logout"/></a>
    </c:if>
</fmt:bundle>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</html>
