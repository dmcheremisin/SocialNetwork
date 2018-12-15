<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="com.social.network.models.User" %>

<c:if test="${empty user.image}">
    <a href="/profile?id=${user.id}">
        <img class="img-circle img-thumbnail img-message" src="./img/noname.svg"/>
            ${user.firstName} ${user.lastName}
    </a>
</c:if>
<c:if test="${not empty user.image}">
    <a href="/profile?id=${user.id}">
        <img class="img-circle img-thumbnail img-message" src="./avatars/${user.image}"/>
            ${user.firstName} ${user.lastName}
    </a>
</c:if>