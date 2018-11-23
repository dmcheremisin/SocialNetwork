<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

            <c:forEach items="${lastMessages}" var="message">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="card">
                            <c:if test="${empty message.sender.image}">
                                <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/noname.svg" />${message.sender.firstName}</a>
                            </c:if>
                            <c:if test="${not empty message.sender.image}">
                                <a href="#"><img class="img-circle img-thumbnail img-message" src="./avatars/${message.sender.image}" /> ${message.sender.firstName}</a>
                            </c:if>

                        </div>
                    </div>
                    <div class="panel-body"><a href="/conversation">${message.message}</a></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>