<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06-Sep-18
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:bundle basename="page_strings" prefix="string.">
        <title><fmt:message key="create_room_type_title"/></title>
    </fmt:bundle>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<fmt:bundle basename="page_strings" prefix="string.">
    <div class="container">
        <div class="col col-md-8 col-lg-6 offset-md-2 offset-lg-3">
            <form role="form" method="post" action="<c:url value="/app/create_room_type"/>">
                <input type="hidden" value="${reservation_id}" name="reservation_id">
                <h2 class="form-heading"><fmt:message key="create_room"/></h2>
                <table class="w-100">
                    <tr>
                        <td>
                            <fmt:message key="name_en"/>:
                        </td>
                        <td>
                            <input type="text" name="name_en" class="form-control" value="${name_en}" required autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="name_ua"/>:
                        </td>
                        <td>
                            <input type="text" name="name_ua" class="form-control" value="${name_ua}" required autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="description_en"/>:
                        </td>
                        <td>
                            <input type="text" name="description_en" class="form-control" value="${description_en}" required autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="description_ua"/>:
                        </td>
                        <td>
                            <input type="text" name="description_ua" class="form-control" value="${description_ua}" required autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="image_path"/>:
                        </td>
                        <td>
                            <input type="text" name="image_path" class="form-control" value="${image_path}" required autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="places"/>:
                        </td>
                        <td>
                            <input type="number" name="places" class="form-control" value="${places}" required autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="price_per_night"/>:
                        </td>
                        <td>
                            <input type="number" name="price_per_night" class="form-control" value="${price_per_night}" required autofocus>
                        </td>
                    </tr>
                </table>
                <br>
                ${error}
                <input class="btn btn-primary w-100 col col-md-6 offset-md-3" type="submit"
                       value="<fmt:message key="create"/>">
            </form>
        </div>
    </div>
</fmt:bundle>
</body>
</html>
