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
        <th>Price</th>
         <th>Operations</th>
    </tr>

    <c:forEach var="dish" items="${dishes}" >

    <c:url var="addToOrder" value="/api/order/addDish">
        <c:param name="dishId" value="${dish.id}" />
    </c:url>

     <tr>
         <td>${dish.name}</td>
         <td>${dish.description}</td>
         <td>${dish.price}</td>
         <td>
         <input type="button" value="Add" onclick="window.location.href = '${addToOrder}'" />
         </td>
     </tr>

    </c:forEach>
</table>

<input type="button" value="Back" onclick="window.location.href = 'menu'"/>

</body>
</html>