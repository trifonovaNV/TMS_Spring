<%@ page import="java.util.List, com.tms.library.model.Book" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.html"/>
<body>
<p>
<h1>Add genre:</h1>
</p>

<form:form action="/lesson24_war/genres/add" method="post" modelAttribute="genreOb">
    <table>
        <tr>
            <td>
                <form:label path="id">Genre ID</form:label>
            </td>
            <td>
                <form:input path="id"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="name">Genre name</form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
    </table>
    <form:button type="submit">Save</form:button>
</form:form>

</body>
<jsp:include page="footer.html"/>
