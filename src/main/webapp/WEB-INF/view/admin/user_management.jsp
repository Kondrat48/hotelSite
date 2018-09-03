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
            <th><fmt:message key="password"/></th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="surname"/></th>
            <th><fmt:message key="phone_number"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="money"/></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.email}</td>
                <td><fmt:message key="role.${fn:toLowerCase(user.role)}"/></td>
                <td>${user.money/100.0}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
    <%--<form method="post" action="/app/user_management_page?records_per_page=${records_per_page}&current_page=${current_page-1}&sort_column=${sort_column}">--%>
        <%--<input type="number" value="${records_per_page}" name="records_per_page">--%>
    <%--</form>--%>

    <ul class="pagination">
        <c:if test="${current_page != 1}">
            <li class="page-item"><a class="page-link"
                                     href="/app/user_management_page?records_per_page=${records_per_page}&current_page=${current_page-1}&sort_column=${sort_column}">Previous</a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${no_of_pages}" var="i">
            <c:choose>
                <c:when test="${current_page eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="/app/user_management_page?records_per_page=${records_per_page}&current_page=${i}&sort_column=${sort_column}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${current_page lt no_of_pages}">
            <li class="page-item"><a class="page-link"
                                     href="/app/user_management_page?records_per_page=${records_per_page}&current_page=${current_page+1}&sort_column=${sort_column}">Next</a>
            </li>
        </c:if>
    </ul>
</fmt:bundle>

</body>
</html>
