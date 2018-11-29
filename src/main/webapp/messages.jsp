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
            <h2>Messages</h2>

            <c:if test="${not empty recentMessages}">
                <c:forEach items="${recentMessages}" var="message">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="card">
                                <avatar:avatar user="${message.sender}" />&nbsp; to &nbsp;<avatar:avatar user="${message.receiver}" />
                            </div>
                        </div>
                        <div class="panel-body"><a href="/conversation?companion=${message.companion}">${message.date} ${message.message}</a></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty recentMessages}">
                <h4>No messages yet...</h4>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>