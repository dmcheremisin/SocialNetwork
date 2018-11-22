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

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="card">
                        <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/noname.svg" alt="Your profile image" /> Mike</a>
                    </div>
                </div>
                <div class="panel-body"><a href="/conversation">Hello! How are you?</a></div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="card">
                        <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/noname.svg" alt="Your profile image" /> Andrew</a>
                    </div>
                </div>
                <div class="panel-body"><a href="/conversation">Hi! I just wanted to remind you about tomorrow meeting?</a></div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="card">
                        <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/noname.svg" alt="Your profile image" /> Liza</a>
                    </div>
                </div>
                <div class="panel-body"><a href="/conversation">Hello! Are you going to club today?</a></div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>