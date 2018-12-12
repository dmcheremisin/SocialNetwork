<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<jsp:include page="../parts/header.jsp">
    <jsp:param name="title" value="Social Network" />
</jsp:include>

<div class="jumbotron">
    <div class="container">
        <h1><fmt:message key='blocked.h1' /></h1>
        <p><fmt:message key='blocked.p1' /></p>
        <p><fmt:message key='blocked.p2' /></p>
        <p><a href="../" class="btn btn-primary btn-lg"><fmt:message key='to.main.page' /> &raquo</a></p>
    </div>
</div>

<jsp:include page="../parts/footer.jsp" />