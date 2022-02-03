<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<h2>All users</h2>
<br>

<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Balance</th>
         <th>Operations</th>
    </tr>

    <c:forEach var="us" items="${users}" >

    <c:url var="updateButton" value="/api/users/updateUser">
        <c:param name="usId" value="${us.id}" />
    </c:url>

    <c:url var="deleteButton" value="/api/users/deleteUser">
            <c:param name="usId" value="${us.id}" />
    </c:url>

     <tr>
         <td>${us.name}</td>
         <td>${us.surname}</td>
         <td>${us.phone}</td>
         <td>${us.email}</td>
         <td>${us.balance}</td>
         <td>
         <input type="button" value="Update" onclick="window.location.href = '${updateButton}'" />
         <input type="button" value="Delete" onclick="window.location.href = '${deleteButton}'" />
         </td>
     </tr>

    </c:forEach>
</table>

<input type="button" value="Add" onclick="window.location.href = 'addUser'"/>

</body>
</html>