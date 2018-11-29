<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="com.social.network.models.User" %>

<c:if test="${user.sex == 2}">Male</c:if>
<c:if test="${user.sex == 3}">Female</c:if>