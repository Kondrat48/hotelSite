<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<head>
    <link rel="stylesheet" href="../../css/menu.css">
</head>
<body>

<c:set var="language"
       value="${not empty param.language ?
       param.language : not empty language ?
       language: pageContext.request.locale.language=='uk' ?
       'uk_UA':'en_US'}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="menu_items" prefix="item.">
    <header class="align-middle navbar navbar-expand navbar-dark flex-column flex-md-row bg-dark align-items-center">
        <a class="navbar-brand mr-0 mr-md-2" href="<c:url value="/app/"/>">Bron'</a>
        <div class="collapse navbar-collapse align-middle" id="navbarSupportedContent">
            <ul class="navbar-nav flex-row align-items-center">
                <c:choose>
                    <c:when test="${sessionScope.role=='guest'}">
                        <li class="nav-item"><a class="nav-link" href="<c:url value="/app/login_page"/>"><fmt:message
                                key="login"/></a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value="/app/register_page"/>"><fmt:message
                                key="register"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${sessionScope.role!='banned'}">
                            <li class="nav-item"><a class="nav-link"
                                                    href="<c:url value="/app/booking_page"/>"><fmt:message
                                    key="booking"/></a></li>
                            <c:if test="${sessionScope.role=='manager'||sessionScope.role=='admin'}">
                                <li class="nav-item dropdown">
                                    <button class="nav-item nav-link btn btn-link dropdown-toggle" type="button"
                                            id="management" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                        <fmt:message key="management"/>
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="management">
                                        <a class="dropdown-item"
                                           href="<c:url value="/app/book_management_page"/>"><fmt:message
                                                key="book_management"/></a>
                                        <a class="dropdown-item"
                                           href="<c:url value="/app/room_management_page"/>"><fmt:message
                                                key="room_management"/></a>
                                        <a class="dropdown-item"
                                           href="<c:url value="/app/room_confirmation_page"/>"><fmt:message
                                                key="confirmation_management"/></a>
                                        <a class="dropdown-item"
                                           href="<c:url value="/app/user_management_page"/>"><fmt:message
                                                key="user_management"/></a>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.role=='admin'}">
                                <li class="nav-item dropdown">
                                    <button class="nav-item nav-link btn btn-link dropdown-toggle" type="button"
                                            id="administration" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                        <fmt:message key="administration"/>
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="administration">
                                        <a class="dropdown-item"
                                           href="<c:url value="/app/session_management_page"/>"><fmt:message
                                                key="session_management"/></a>
                                    </div>
                                </li>
                            </c:if>
                        </c:if>
                    </c:otherwise>
                </c:choose>

            </ul>

            <ul class="navbar-nav flex-row ml-md-auto <%--d-none --%>d-md-flex align-items-center">

                <c:choose>
                    <c:when test="${sessionScope.role=='guest'}">
                        <fmt:bundle basename="page_strings" prefix="string.">
                            <li class="nav-item align-items-center"><span class="navbar-text "><fmt:message
                                    key="role.guest"/></span></li>
                        </fmt:bundle>
                    </c:when>
                    <c:otherwise>
                        <fmt:bundle basename="page_strings" prefix="string.">
                            <li class="nav-item">
                                <form class="form-profile" action="<c:url value="/app/profile_page"/>">
                                    <input type="submit" class="nav-item nav-link btn btn-link"
                                           value="<fmt:message key="role.${sessionScope.role}"/>: ${sessionScope.username}"/>
                                </form>
                            </li>
                        </fmt:bundle>
                        <li class="nav-item">
                            <form class="form-logout" action="<c:url value="/app/logout"/>">
                                <input type="submit" class="btn btn-danger" value="<fmt:message key="logout"/>"/>
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>

                <li class="nav-item dropdown">
                    <button class="nav-item nav-link btn btn-link dropdown-toggle<%-- mr-md-2--%>" type="button"
                            id="languages-btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${language == 'uk_UA' ?'Ukrainian':'English'}
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="languages-btn">
                        <a class="dropdown-item${language == 'en_US' ? ' active' : ''}"
                           href="${requestScope.javax.servlet.forward.query_string}${fn:contains(requestScope.javax.servlet.forward.query_string, '?')?'&':'?'}language=en_US">English</a>
                        <a class="dropdown-item${language == 'uk_UA' ? ' active' : ''}"
                           href="${requestScope.javax.servlet.forward.query_string}${fn:contains(requestScope.javax.servlet.forward.query_string, '?')?'&':'?'}language=uk_UA">Ukrainian</a>
                    </div>
                </li>
            </ul>
                <%--<form class="form-inline my-2 my-lg-0" &lt;%&ndash;onsubmit="location.reload(true)"&ndash;%&gt;--%>
                <%--action="${requestScope.javax.servlet.forward.request_uri}">--%>
                <%--<select id="language" name="language" onchange="submit()">--%>
                <%--<option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>--%>
                <%--<option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>--%>
                <%--</select>--%>
                <%--</form>--%>
        </div>
    </header>
</fmt:bundle>
</body>
</html>
