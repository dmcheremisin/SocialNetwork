<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<div class="sidebar-header"></div>
<div class="panel panel-default ">
    <div class="panel-heading">
        <h3 class="panel-title"><fmt:message key="menu.user.menu" /></h3>
    </div>
    <div class="list-group">
        <a href="/profile" class="list-group-item"><fmt:message key="menu.profile" /></a>
        <a href="/messages" class="list-group-item"><fmt:message key="menu.messages" /></a>
        <a href="/friends" class="list-group-item"><fmt:message key="menu.friends" /></a>
        <a href="/users" class="list-group-item"><fmt:message key="menu.users" /></a>
        <a href="/settings" class="list-group-item"><fmt:message key="menu.settings" /></a>
    </div>
</div>