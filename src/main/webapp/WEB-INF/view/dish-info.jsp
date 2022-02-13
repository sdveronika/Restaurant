<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>

<h2>User info</h2>
<br>

<form:form action="saveDish" method="post" modelAttribute="dish">

    <form:hidden path="id" />

    Name <form:input path="name"/>
    <br><br>
    Description <form:input path="description"/>
    <br><br>
    Category <form:input path="category"/>
    <br><br>
    Price <form:input path="price"/>
    <br><br>
    <br><br>
    <input type="submit" value="Ok"/>

</form:form>

</body>
</html>