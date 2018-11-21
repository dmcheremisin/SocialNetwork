<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="sidebar-header">
    <div class="card">
        <c:if test="${empty user.image}">
            <img class="img-circle img-thumbnail social-img" src="img/noname.svg" alt="Your profile image">
        </c:if>
        <c:if test="${not empty user.image}">
            <img class="img-circle img-thumbnail social-img" src="avatars/${user.image}" alt="Your profile image">
        </c:if>
    </div>
    <h4>Your name</h4>
</div>
<div class="panel panel-default ">
    <div class="panel-heading">
        <h3 class="panel-title">User menu</h3>
    </div>
    <div class="list-group">
        <a href="/profile" class="list-group-item">Profile</a>
        <a href="/messages" class="list-group-item">Messages</a>
        <a href="/friends" class="list-group-item">Friends</a>
        <a href="/users" class="list-group-item">Users</a>
    </div>
</div>
<c:if test="${role eq 'admin'}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Admin menu</h3>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item list-group-item-success">Make admin</a>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item list-group-item-danger">Block user</a>
        </div>
    </div>
</c:if>
