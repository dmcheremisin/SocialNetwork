<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${param.title}</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/jquery-ui.css" rel="stylesheet">
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
            <a class="navbar-brand" href="./">Social Network</a>
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
                    <button type="submit" class="btn btn-success">Login</button>
                </form>
            </c:if>
            <div class="navbar-right">
                <a href="#" class="navbar-brand">Ru</a>
                <a href="#" class="navbar-brand">En</a>
                <c:if test="${user != null}">
                    <a href="/logout" class="navbar-brand">Logout</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<hr/>
