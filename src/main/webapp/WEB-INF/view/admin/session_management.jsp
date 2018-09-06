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
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="sessing_management_title"></fmt:message></title>
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
            <th>

            </th>
        </tr>
        <c:forEach var="sessionDto" items="${requestScope.sessions}">
            <tr>
                <td class="align-middle">
                    <c:if test="${sessionDto.role!='guest'}">
                        <a class="table-button-form"
                           href="/app/user_management?search_field=username&search_param=${sessionDto.username}">${sessionDto.username}
                        </a>
                    </c:if>
                    <c:if test="${sessionDto.role=='guest'}">
                        ${sessionDto.username}
                    </c:if>
                </td>
                <td class="align-middle">
                    <fmt:message key="role.${sessionDto.role}"/>
                </td>
                <td class="align-middle">
                        ${sessionDto.creationTime}
                </td>
                <td class="align-middle">
                        ${sessionDto.lastActivity}
                </td>
                <td class="align-middle">
                        ${sessionDto.language}
                </td>
                <td class="align-middle">
                        ${sessionDto.sessionId}
                </td>
                <td class="align-middle">
                    <a class="btn btn-danger"
                       href="/app/session_management_page?records_per_page=${records_per_page}&current_page=${current_page}&sort_column=${sort_column}&session_to_remove=${sessionDto.username}">
                        <fmt:message key="invalidate"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:import url="../pagination_footer.jsp"/>
</fmt:bundle>


</body>
</html>
