<%@ page import="java.util.List, com.tms.library.model.Book" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.html"/>
<body>
<p>
    <h1>Books:</h1>
</p>


<c:choose>
    <c:when test="${not empty books}">
        <ul>
            <c:forEach var="book" items="${books}">
                <li>${book}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        No books...
    </c:otherwise>
</c:choose>

</body>
<jsp:include page="footer.html"/>
