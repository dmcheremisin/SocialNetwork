<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<jsp:include page="parts/header.jsp">
    <jsp:param name="title" value="Social Network" />
</jsp:include>

<div class="jumbotron">
    <div class="container">
        <h1><fmt:message key="index.intro" />:</h1>
        <ul class="list-inline">
            <li>&bull; <fmt:message key="index.registration" /></li>
            <li>&bull; <fmt:message key="index.authorization" /></li>
            <li>&bull; <fmt:message key="index.profile" /></li>
            <li>&bull; <fmt:message key="index.user.search" /></li>
            <li>&bull; <fmt:message key="index.friends" /></li>
            <li>&bull; <fmt:message key="index.messages" /></li>
        </ul>
        <hr />
        <p><fmt:message key="index.join.today" /></p>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <h1><fmt:message key="index.register.today" /></h1>
            <div class="form-group">
                <form action="/register" method="post">
                    <div class="form-group">
                        <label for="email"><fmt:message key="email" />: <input type="text" name="email" class="form-control" id="email" /></label>
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="index.password" />: <input type="password" name="password" class="form-control" id="password" /></label>
                    </div>
                    <div class="form-group">
                        <label for="conf-password"><fmt:message key="index.confirm.password" />: <input type="password" name="password-confirm" class="form-control" id="conf-password" /></label>
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" value="<fmt:message key="index.register" /> &raquo" class="btn btn-lg btn-success" /></label>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" />