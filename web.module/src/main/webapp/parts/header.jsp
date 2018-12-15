<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${param.title}</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/jquery-ui.min.css" rel="stylesheet">
    <link href="../css/custom.css" rel="stylesheet">

</head>
<body>

<div class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./"><fmt:message key="header" /></a>
        </div>
        <div class="navbar-collapse collapse">
            <c:if test="${user == null}">
                <form action="/login" method="post" class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" name="email" placeholder="Email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" placeholder="Password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success"><fmt:message key="login" /></button>
                </form>
            </c:if>
            <div class="navbar-right">
                <a href="/locale?lang=ru" class="navbar-brand lang <c:if test="${lang ne 'en'}">langSelected</c:if>">
                    <fmt:message key="russian" />
                </a>
                <a href="/locale?lang=en" class="navbar-brand lang <c:if test="${lang eq 'en'}">langSelected</c:if>">
                    <fmt:message key="english" />
                </a>
                <c:if test="${user != null}">
                    <a href="/logout" class="navbar-brand"><fmt:message key="logout" /></a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<hr/>
