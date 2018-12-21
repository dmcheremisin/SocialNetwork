<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="localizationName" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="errorMessage" required="true" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="error" required="false" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<div class="form-group <c:if test="${not empty error and error eq name}">has-error</c:if>">
    <label for="${name}">
        <fmt:message key='${localizationName}' />: <input type="${type}" name="${name}" class="form-control ${name}" id="${name}" value="${value}" />
    </label>
    <div class="help-block ${name}_error <c:if test="${empty error or error ne name}">hidden</c:if>">
        <fmt:message key='${errorMessage}' />
    </div>
</div>