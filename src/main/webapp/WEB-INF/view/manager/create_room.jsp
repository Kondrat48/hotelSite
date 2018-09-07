<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06-Sep-18
  Time: 01:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="create_room_title"/></title>
    </fmt:bundle>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<fmt:bundle basename="page_strings" prefix="string.">
    <div class="container">
        <div class="col col-md-8 col-lg-6 offset-md-2 offset-lg-3">
            <form role="form" method="post" action="<c:url value="/app/create_room"/>">
                <input type="hidden" value="${reservation_id}" name="reservation_id">
                <h2 class="form-heading"><fmt:message key="create_room"/></h2>
                <table class="w-100">
                    <tr>
                        <td>
                            <fmt:message key="number"/>:
                        </td>
                        <td>
                            <input type="number" name="room_id" class="form-control" value="${room_id}" required autofocus>
                                ${existroom}
                        </td>
                        <td>
                            <select class="custom-select-sm form-control" name="room_type_id">
                                <c:forEach var="roomType" items="${roomTypes}">
                                    <option value="${roomType.id}"${roomType.id==room_type_id?' selected':''}>${roomType.typeName}</option>
                                </c:forEach>
                                <option value="other"><fmt:message key="other"/></option>
                            </select>
                                ${notexistroomtype}
                                ${wrongnumber}
                        </td>
                    </tr>

                </table>
                <br>
                <input class="btn btn-primary w-100 col col-md-6 offset-md-3" type="submit"
                       value="<fmt:message key="create"/>">
            </form>
        </div>
    </div>
</fmt:bundle>

</body>
</html>
