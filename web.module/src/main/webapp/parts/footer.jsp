<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />

<div class="container">
    <hr/>
    <footer>
        <p>&copy; <fmt:message key="footer.copyright" /></p>
    </footer>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/validation.js"></script>

<c:if test="${param.specificScript != null}">
    <script src="${param.specificScript}"></script>
</c:if>

</body>
</html>