<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="url" required="true" %>

<form action="/${url}" method="get">
    <div class="input-group stylish-input-group">
        <input type="text" class="form-control" name="search" placeholder="Search">
        <span class="input-group-addon">
                        <button type="submit">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
    </div>
</form>