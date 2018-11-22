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
            <h2>Conversation</h2>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="card">
                        <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/noname.svg" alt="Your profile image" />Mike</a>
                    </div>
                </div>
                <div class="panel-body"><div class="message-left">22.11.2018 12:25</div> <div class="message-left">Hello! How are you?</div></div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="card">
                        <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/noname.svg" alt="Your profile image" />You</a>
                    </div>
                </div>
                <div class="panel-body"><div class="message-left">22.11.2018 13:35</div> <div class="message-left">I'm fine thank you =)</div></div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    New message
                </div>
                <div class="panel-body">
                    <form method="post">
                        <div class="form-group">
                            <textarea class="form-control" rows="5" id="comment"></textarea>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" value="Send &raquo" class="btn btn-success"/></label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>