<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="com.social.network.models.User" %>

<c:if test="${empty user.image}">
        <img class="img-circle img-thumbnail social-img" src="./img/noname.svg"/>
</c:if>
<c:if test="${not empty user.image}">
        <img class="img-circle img-thumbnail social-img" src="./avatars/${user.image}"/>
</c:if>