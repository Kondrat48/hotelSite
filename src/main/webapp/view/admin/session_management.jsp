<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29-Aug-18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<fmt:bundle basename="page_strings" prefix="string.">
    <table>
        <tr>
            <th>
                <fmt:message key="username"/>
            </th>
            <th>
                <fmt:message key="role"/>
            </th>
            <th>
                <fmt:message key="creation"/>
            </th>
            <th>
                <fmt:message key="last_activity"/>
            </th>
            <th>
                <fmt:message key="language"/>
            </th>
            <th>
                <fmt:message key="session_number"/>
            </th>
        </tr>
        <c:forEach var="sessionDto" items="${requestScope.sessions}">
            <tr>
                <td>
                    <c:if test="${sessionDto.role!='guest'}">
                        <form action="/app/edit_user_page">
                            <input type="hidden" name="username" value="${sessionDto.username}">
                            <input type="submit" value="${sessionDto.username}">
                        </form>
                    </c:if>
                    <c:if test="${sessionDto.role=='guest'}">
                        ${sessionDto.username}
                    </c:if>
                </td>
                <td>
                    <fmt:message key="role.${sessionDto.role}"/>
                </td>
                <td>
                        ${sessionDto.creationTime}
                </td>
                <td>
                        ${sessionDto.lastActivity}
                </td>
                <td>
                        ${sessionDto.language}
                </td>
                <td>
                        ${sessionDto.sessionId}
                </td>
                <td>
                    <form action="/app/session_management_page">
                        <input type="hidden" name="session_to_remove" value="${sessionDto.username}">
                        <input type="submit" value="<fmt:message key="invalidate"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>


</body>
</html>
