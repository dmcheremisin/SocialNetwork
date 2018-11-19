<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/header.jsp">
    <jsp:param name="title" value="Social Network" />
</jsp:include>

<div class="jumbotron">
    <div class="container">
        <h1>Social network facilities:</h1>
        <ul class="list-inline">
            <li>&bull; Registration</li>
            <li>&bull; Authorization</li>
            <li>&bull; Profile view and change</li>
            <li>&bull; User search</li>
            <li>&bull; Friends</li>
            <li>&bull; Messages</li>
        </ul>
        <hr />
        <p>Join social network and become a member with full capabilities!</p>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <h1>Register today!</h1>
            <div class="form-group">
                <form action="/register" method="post">
                    <div class="form-group">
                        <label for="email">Email: <input type="text" name="email" class="form-control" id="email" /></label>
                    </div>
                    <div class="form-group">
                        <label for="password">Password: <input type="password" name="password" class="form-control" id="password" /></label>
                    </div>
                    <div class="form-group">
                        <label for="conf-password">Confirm Password: <input type="password" name="password-confirm" class="form-control" id="conf-password" /></label>
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" value="Register &raquo" class="btn btn-lg btn-success" /></label>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" />