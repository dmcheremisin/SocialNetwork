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
            <div class="friends-top">
                <form action="./friends.html" method="get">
                    <div class="input-group stylish-input-group">
                        <input type="text" class="form-control" name="search" placeholder="Search">
                        <span class="input-group-addon">
                        <button type="submit">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
                    </div>
                </form>
            </div>

            <div class="friends-top">
                <h3>Friends requests</h3>
                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/sansa.jpg"/>Sansa Stark</a>
                        <td>
                            <button class="btn btn-success">Accept</button>
                        </td>
                        <td>
                            <button class="btn btn-danger">Decline</button>
                        </td>
                        <td>
                            <button class="btn btn-info">Message</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/arya.jpg"/>Arya Stark</a>
                        <td>
                            <button class="btn btn-success">Accept</button>
                        </td>
                        <td>
                            <button class="btn btn-danger">Decline</button>
                        </td>
                        <td>
                            <button class="btn btn-info">Message</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="friends-top">
                <h3>Your friends</h3>
                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/2.png"/>Cersei
                                Lanister</a>
                        <td>
                            <button class="btn btn-info">Message</button>
                        </td>
                        <td>
                            <button class="btn btn-danger">Remove</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#"><img class="img-circle img-thumbnail img-message" src="./img/3.jpeg"/>Jaime
                                Lanister</a>
                        </td>
                        <td>
                            <button class="btn btn-info">Message</button>
                        </td>
                        <td>
                            <button class="btn btn-danger">Remove</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp" >
    <jsp:param name="specificScript" value="js/profile.js" />
</jsp:include>