<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="avatar" tagdir="/WEB-INF/tags" %>

<jsp:include page="parts/header.jsp">
    <jsp:param name="title" value="Profile" />
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="parts/userMenu.jsp" />
        </div>
        <div class="col-md-9">
            <h2>Your avatar.</h2>
            <div class="profile-block">
                <div>
                    <div class="image-drop border-dotted" ondrop="dragAndDrop(event)" ondragover="dragEnter(event)" ondragenter="dragEnter(event)" ondragleave="dragLeave(event)">
                        <p class="drop-text">Drop your image here.</p>
                    </div>
                    <div class="btn-vertical">
                        <button class="btn btn-lg btn-success btn-left">Update avatar</button>
                    </div>
                    <div class="image-right">
                        <div class="card">
                            <avatar:profileImage user="${user}" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="profile-block">
                <h2>Your profile.</h2>
                <form action="/settings" method="post">
                    <div class="panel panel-default">
                        <div class="list-group">

                            <div class="list-group-item">
                                <label for="email">Email: <input type="text" name="email" class="form-control"
                                                                 id="email" value="${user.email}" disabled/>
                                </label>
                            </div>
                            <div class="list-group-item">
                                <label for="firstname">First name: <input type="text" name="firstName"
                                                                          class="form-control" value="${user.firstName}"
                                                                          id="firstname"/>
                                </label>
                            </div>
                            <div class="list-group-item">
                                <label for="lastname">Last name: <input type="text" name="lastName"
                                                                        class="form-control" value="${user.lastName}"
                                                                        id="lastname"/>
                                </label>
                            </div>
                            <div class="list-group-item">
                                <label for="datepicker">Birth date:
                                    <input type="text" name="dob" class="form-control" value="${user.dob}"
                                           id="datepicker">
                                </label>
                            </div>
                            <div class="list-group-item"> Gender:
                                <label class="radio-inline">
                                    <input type="radio" name="sex" value="2" <c:if test="${user.sex == 2}">checked</c:if> >
                                    Male
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="sex" value="3" <c:if test="${user.sex == 3}">checked</c:if> >
                                    Female
                                </label>
                            </div>
                            <div class="list-group-item">
                                <label for="phone">Phone: <input type="text" name="phone"
                                                                 class="form-control" value="${user.phone}"
                                                                 id="phone"/></label>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-lg btn-success">Change profile</button>
                </form>
            </div>
            <div class="profile-block">
                <h2>Change password</h2>
                <form action="/updatePassword" method="post">
                    <div class="panel panel-default">
                        <div class="list-group">

                            <div class="list-group-item">
                                <label for="oldpassword">Old Password: <input type="password" name="oldPassword"
                                                                              class="form-control"
                                                                              id="oldpassword"/></label>
                            </div>
                            <div class="list-group-item">
                                <label for="password">New Password: <input type="password" name="password"
                                                                              class="form-control"
                                                                              id="password"/></label>
                            </div>
                            <div class="list-group-item">
                                <label for="password-confirm">Repeat Password: <input type="password"
                                                                                    name="password-confirm"
                                                                                    class="form-control"
                                                                                    id="password-confirm"/></label>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-lg btn-success">Change password</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>