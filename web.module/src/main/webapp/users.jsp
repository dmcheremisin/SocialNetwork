<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="socialTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<jsp:include page="parts/header.jsp">
    <jsp:param name="title" value="Profile" />
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="parts/userMenu.jsp" />
        </div>
        <div class="col-md-9">
            <div class="friends-top">
                <socialTags:userSearch url="users" />
            </div>

            <div class="friends-top">
                <h3><fmt:message key='users.header' />:</h3>
                <c:if test="${not empty users}">
                    <table class="table users">
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>
                                    <socialTags:avatar user="${user}"/><br />
                                    ${user.dob}, <socialTags:gender user="${user}" />
                                </td>
                                <td>
                                    <a href="/conversation?companion=${user.id}" class="btn btn-info"><fmt:message key='message' /></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div>
                        <nav>
                            <ul class="pagination">
                                <li class='page-item <c:if test="${page == 0}">disabled</c:if>'>
                                    <a class="page-link" href="/users?page=${page - 1}&search=${search}">Previous</a>
                                </li>
                                <li class='page-item <c:if test="${fn:length(users) lt 10}">disabled</c:if>'>
                                    <a class="page-link" href="/users?page=${page + 1}&search=${search}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </c:if>
                <c:if test="${empty users}">
                    <h4><fmt:message key='users.no.users' /></h4>
                </c:if>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" />