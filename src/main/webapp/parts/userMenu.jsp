<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="sidebar-header"></div>
<div class="panel panel-default ">
    <div class="panel-heading">
        <h3 class="panel-title">User menu</h3>
    </div>
    <div class="list-group">
        <a href="/profile" class="list-group-item">Profile</a>
        <a href="/messages" class="list-group-item">Messages</a>
        <a href="/friends" class="list-group-item">Friends</a>
        <a href="/users" class="list-group-item">Users</a>
        <a href="/settings" class="list-group-item">Settings</a>
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
