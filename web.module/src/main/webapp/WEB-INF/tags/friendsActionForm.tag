<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="com.social.network.models.User" %>
<%@ attribute name="button" required="true" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="name" required="true" %>


<form method="post" action="/friends">
    <input type="hidden" name="friend" value="${user.id}" />
    <button class="btn btn-${button}" type="submit" name="action" value="${action}">${name}</button>
</form>