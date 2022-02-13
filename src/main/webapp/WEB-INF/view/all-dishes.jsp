<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<h2>All dishes</h2>
<br>

<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
         <th>Operations</th>
    </tr>

    <c:forEach var="dish" items="${dishes}" >

    <c:url var="updateButton" value="/api/dishes/updateDish">
        <c:param name="dishId" value="${dish.id}" />
    </c:url>

    <c:url var="deleteButton" value="/api/dishes/deleteDish">
            <c:param name="dishId" value="${dish.id}" />
    </c:url>

     <tr>
         <td>${dish.name}</td>
         <td>${dish.description}</td>
         <td>${dish.category}</td>
         <td>${dish.price}</td>
         <td>
         <input type="button" value="Update" onclick="window.location.href = '${updateButton}'" />
         <input type="button" value="Delete" onclick="window.location.href = '${deleteButton}'" />
         </td>
     </tr>

    </c:forEach>
</table>

<input type="button" value="Add" onclick="window.location.href = 'addDish'"/>

</body>
</html>