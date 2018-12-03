<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="socialTags" tagdir="/WEB-INF/tags" %>

<jsp:include page="parts/header.jsp">
    <jsp:param name="title" value="Profile" />
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="parts/userMenu.jsp" />
        </div>
        <div class="col-md-9">
            <div class="profile-block margin-top">
                <table class="table">
                    <tr>
                        <td>
                            <div class="card">
                                <socialTags:profileImage user="${profileUser}"/>
                                <h3>${profileUser.firstName} ${profileUser.lastName}</h3>
                            </div>
                            <table class="table">
                                <c:if test="${profileUser.id != user.id}">
                                    <tr>
                                        <c:if test="${profileUser.id != user.id}">
                                            <c:if test="${!usersHaveFriendship}">
                                                <td>
                                                    <socialTags:friendsActionForm user="${profileUser}" button="success" action="addToFriends" name="Add to friends" />
                                                </td>
                                            </c:if>
                                            <c:if test="${usersHaveFriendship}">
                                                <td>
                                                    <a href="/friends" class="btn btn-success">Friend <span class="glyphicon glyphicon-ok"></span></a>
                                                </td>
                                            </c:if>
                                        </c:if>
                                    </tr>
                                    <tr>
                                        <td>
                                            <form method="get" action="/conversation">
                                                <button class="btn btn-info" name="companion" value="${profileUser.id}"
                                                        type="submit">Send message
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                        <td class="profile-info">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3>Profile Info</h3>
                                </div>
                                <div class="list-group">
                                    <p class="list-group-item">First name: ${profileUser.firstName}</p>
                                    <p class="list-group-item">Last name: ${profileUser.lastName}</p>
                                    <p class="list-group-item">Birth date: ${profileUser.dob}</p>
                                    <p class="list-group-item">Gender:
                                        <socialTags:gender user="${profileUser}" />
                                    </p>
                                    <p class="list-group-item">Phone: ${profileUser.phone}</p>
                                    <p class="list-group-item">Email: ${profileUser.email}</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <c:if test="${role eq 'admin' and profileUser.id ne user.id}">
                        <tr>
                            <td></td>
                            <td>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Admin menu</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="message-left">
                                            <c:if test="${profileUserRole ne 'admin'}">
                                                <form method="post" action="adminAction">
                                                    <input type="hidden" name="userId" value="${profileUser.id}">
                                                    <input type="hidden" name="action" value="makeAdmin">
                                                    <button class="btn btn-success" type="submit">Make Admin</button>
                                                </form>
                                            </c:if>
                                            <c:if test="${profileUserRole eq 'admin'}">
                                                <form method="post" action="adminAction">
                                                    <input type="hidden" name="userId" value="${profileUser.id}">
                                                    <input type="hidden" name="action" value="makeUsual">
                                                    <button class="btn btn-success" type="submit">Make Usual User</button>
                                                </form>
                                            </c:if>
                                        </div>
                                        <div class="message-left">
                                            <c:if test="${profileUser.blocked}">
                                                <form method="post" action="adminAction">
                                                    <input type="hidden" name="userId" value="${profileUser.id}">
                                                    <input type="hidden" name="action" value="unblock">
                                                    <button class="btn btn-danger" type="submit">Unblock</button>
                                                </form>
                                            </c:if>
                                            <c:if test="${!profileUser.blocked}">
                                                <form method="post" action="adminAction">
                                                    <input type="hidden" name="userId" value="${profileUser.id}">
                                                    <input type="hidden" name="action" value="block">
                                                    <button class="btn btn-danger" type="submit">Block</button>
                                                </form>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
            <div class="profile-block">
                <c:if test="${user.id eq profileUser.id}">
                    <h3>Recent Message:</h3>
                    <c:if test="${not empty message}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="card">
                                    <socialTags:avatar user="${message.sender}" />&nbsp; to &nbsp;<socialTags:avatar user="${message.receiver}" />
                                </div>
                            </div>
                            <div class="panel-body"><a href="/conversation?companion=${message.companion}">${message.date} ${message.message}</a></div>
                        </div>
                    </c:if>
                    <c:if test="${empty message}">
                        <h4>No messages found</h4>
                    </c:if>
                </c:if>
            </div>
            <div class="profile-block">
                <h3>Friends:</h3>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <c:if test="${not empty friends}">
                            <c:forEach var="friend" items="${friends}">
                                <span class="profile-friend"><socialTags:avatar user="${friend.friend}"/></span>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty friends}">
                            <h4>No friends yet...</h4>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>