<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05-Sep-18
  Time: 05:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:bundle basename="page_strings" prefix="string.">
    <div class="justify-content-center d-flex p-2">
        <div class="row w-100">
            <ul class="pagination">
                <c:set var="link_addition"
                       value="${not empty user_id ?'&user_id='+user_id+'&reservation_id='+reservation_id:''}"/>
                <c:if test="${current_page != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${records_per_page}&current_page=${current_page-1}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">Previous</a>
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
                                                     href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${records_per_page}&current_page=${i}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${current_page lt no_of_pages}">
                    <li class="page-item"><a class="page-link"
                                             href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${records_per_page}&current_page=${current_page+1}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">Next</a>
                    </li>
                </c:if>
                <div class="dropdown">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false" id="page_selector">
                            ${records_per_page}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="page_selector">
                        <a class="dropdown-item${records_per_page==5?' active':''}"
                           href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${5}&current_page=${1}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">5</a>
                        <a class="dropdown-item${records_per_page==10?' active':''}"
                           href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${10}&current_page=${1}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">10</a>
                        <a class="dropdown-item${records_per_page==15?' active':''}"
                           href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${15}&current_page=${1}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">15</a>
                        <a class="dropdown-item${records_per_page==20?' active':''}"
                           href="<c:url value="${javax.servlet.forward.request_uri}?records_per_page=${20}&current_page=${1}&sort_column=${sort_column}&search_field=${search_field}&search_param=${search_param}${link_addition}"/>">20</a>
                    </div>
                </div>
            </ul>

            <form class="form-inline ml-auto" action="<c:url value="${javax.servlet.forward.request_uri}"/>">
                <div class="form-group">
                    <input type="hidden" name="records_per_page" value="${records_per_page}">
                    <input type="hidden" name="sort_column" value="${sort_column}">
                    <select class="custom-select-sm form-control" name=search_field>
                        <c:forEach var="item" items="${search_fields}">
                            <option value="${item}"${search_field==item?' selected':''}><fmt:message
                                    key="${item}"/></option>
                        </c:forEach>
                    </select>
                    <input class="form-control" name="search_param" type="text" placeholder="<fmt:message key="search"/>"
                           value="${search_param}">
                    <button class="btn btn-success" type="submit"><fmt:message key="search"/></button>
                </div>
            </form>
        </div>
    </div>
</fmt:bundle>
</html>
