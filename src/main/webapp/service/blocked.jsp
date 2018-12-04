<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../parts/header.jsp">
    <jsp:param name="title" value="Social Network" />
</jsp:include>

<div class="jumbotron">
    <div class="container">
        <h1>Hello, your account is blocked!</h1>
        <p>It seems that you have made something wrong, so we had to block your account.</p>
        <p>Please, keep calm and breeth. If you are certainly sure that there is a mistake than contact our
            <a href="mailto:ask@socialnetwork.com">Technical support</a></p>
        <p><a href="../" class="btn btn-primary btn-lg">Go back to main page &raquo</a></p>
    </div>
</div>

<jsp:include page="../parts/footer.jsp" />