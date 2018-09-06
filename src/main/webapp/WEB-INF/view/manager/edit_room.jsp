<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05-Sep-18
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="edit_room_title"/></title>
    </fmt:bundle>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<fmt:bundle basename="page_strings" prefix="string.">
    <div class="container">
        <div class="col col-md-8 col-lg-6 offset-md-2 offset-lg-3">
            <form role="form" method="post" action="${pageContext.request.contextPath}/app/create_room">
                <h2 class="form-heading"><fmt:message key="edit_room"/></h2>
                <input type="hidden" name="old_room_id" value="${roomDao.number}"/>
                <table class="w-100">
                    <tr>
                        <td>
                            <fmt:message key="number"/>:
                        </td>
                        <td>
                            <input type="number" name="room_id" class="form-control" value="${roomDto.number}"
                                   required autofocus>
                                ${existnumber}
                                ${wrongnumber}
                        </td>
                        <td>
                            <select class="custom-select-sm form-control" name="room_type_id">
                                <c:forEach var="roomType" items="${roomTypes}">
                                    <option value="${roomType.id}"${roomType.id==roomDto.roomTypeId?' selected':''}>roomType.name</option>
                                </c:forEach>
                                <option value="other"><fmt:message key="other"/></option>
                            </select>
                                ${notexistroomtype}
                        </td>
                    </tr>

                </table>
                <br>
                <input class="btn btn-warning w-100 col col-md-6 offset-md-3" type="submit"
                       value="<fmt:message key="update"/>">
            </form>
            <a class="btn btn-danger w-100 col col-md-6 offset-md-3" href="<c:url value="/app/delete_room?room_id=${roomDto}"/>"><fmt:message key="delete"/></a>
        </div>
    </div>
</fmt:bundle>
</body>
</html>
