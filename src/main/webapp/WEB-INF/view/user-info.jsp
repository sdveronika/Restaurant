<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>

<h2>User info</h2>
<br>

<form:form action="save" method="post" modelAttribute="user">

    <form:hidden path="id" />

    Name <form:input path="name"/>
    <br><br>
    Surname <form:input path="surname"/>
    <br><br>
    Phone <form:input path="phone"/>
    <br><br>
    Email <form:input path="email"/>
    <br><br>
    Password <form:input path="password"/>
    <br><br>
    Role <form:input path="role"/>
    <br><br>
    Balance <form:input path="balance"/>
    <br><br>
    <input type="submit" value="Ok"/>

</form:form>

</body>
</html>