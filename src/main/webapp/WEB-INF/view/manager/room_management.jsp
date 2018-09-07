<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26-Aug-18
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="room_management_title"/></title>
    </fmt:bundle>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../menu.jsp"/>
<fmt:bundle basename="page_strings" prefix="string.">

    <c:set var="redirectParams" value="reservation_id=${reservation_id}"/>
    <c:if test="${not empty user_id}">
        <h2><fmt:message key="user_room_selecting"/></h2>
    </c:if>
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th class="align-middle"><fmt:message key="number"/></th>
            <th class="align-middle"><fmt:message key="type"/></th>
            <c:if test="${not empty user_id}">
                <th class="align-middle"></th>
            </c:if>
            <th class="align-middle">
                <a class="btn btn-success w-100" href="<c:url value="/app/create_room_page?${redirectParams}"/>">
                    <fmt:message key="create"/>
                </a>
            </th>
        </tr>
        <c:forEach var="roomDto" items="${roomDtos}">
            <tr>
                <td class="align-middle">${roomDto.id}</td>
                <td class="align-middle">${roomDto.typeName}</td>
                <c:if test="${not empty reservation_id}">
                    <td class="align-middle">
                        <a class="btn btn-primary w-100"
                           href="<c:url value="/app/create_confirmation?room_id=${roomDto.id}&${redirectParams}"/>">
                            <fmt:message key="select"/>
                        </a>
                    </td>
                </c:if>
                <td class="align-middle">
                    <a class="btn btn-warning w-100" href="<c:url value="/app/edit_room_page?room_id=${roomDto.id}&${redirectParams}"/>">
                        <fmt:message key="edit"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="../pagination_footer.jsp"/>
</fmt:bundle>
</body>
</html>
