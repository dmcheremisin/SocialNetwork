<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="avatar" tagdir="/WEB-INF/tags" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<c:set var="send">
    <fmt:message key='conversation.send' />
</c:set>

<jsp:include page="parts/header.jsp">
    <jsp:param name="title" value="Profile" />
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="parts/userMenu.jsp"/>
        </div>
        <div class="col-md-9">
            <h2><fmt:message key="conversation.with" />
                <avatar:avatar user="${companionUser}" />
            </h2>
            <c:if test="${not empty conversation}">
                <c:forEach items="${conversation}" var="message">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="card">
                                <avatar:avatar user="${message.sender}" />
                            </div>
                        </div>
                        <div class="panel-body">${message.date} ${message.message}</a></div>
                    </div>
                </c:forEach>
            </c:if>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <fmt:message key="conversation.new" />
                </div>
                <div class="panel-body">
                    <form method="post" action="/conversation">
                        <div class="form-group">
                            <textarea class="form-control message-body" rows="5" name="message"></textarea>
                            <input type="hidden" name="companion" value="${companionUser.id}" class="companion-user">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" value="${send} &raquo" class="btn btn-success submit-message"/></label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" />