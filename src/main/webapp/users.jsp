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
            <div class="friends-top">
                <form action="/users" method="get">
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
                <h3>Users:</h3>
                <%--<table class="table users">--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<a href="#"><img class="img-circle img-thumbnail img-message" src="./img/sansa.jpg"/>Sansa Stark</a>--%>
                        <%--<td>--%>
                            <%--<button class="btn btn-success">Add to friends</button>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<a href="#"><img class="img-circle img-thumbnail img-message" src="./img/arya.jpg"/>Arya Stark</a>--%>
                        <%--<td>--%>
                            <%--<span class="btn btn-success">Already friend <span class="glyphicon glyphicon-ok"></span></span>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <c:if test="${not empty users}">
                    <table class="table users">
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>
                                    <socialTags:Avatar user="${user}"/>
                                </td>
                                <td>
                                    <a href="/conversation?companion=${user.id}" class="btn btn-info">Message</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${empty users}">
                    <h4>No users yet...</h4>
                </c:if>
            </div>
        </div>
    </div>
    <hr/>
    <footer>
        <p>&copy; Все права защищены</p>
    </footer>
</div>


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="../js/profile-dnd.js"></script>
</body>
</html>