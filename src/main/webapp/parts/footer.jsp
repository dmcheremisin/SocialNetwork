<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <hr/>
    <footer>
        <p>&copy; All rights reserved</p>
    </footer>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/bootstrap.min.js"></script>

<c:if test="${param.specificScript != null}">
    <script src="${param.specificScript}"></script>
</c:if>

</body>
</html>