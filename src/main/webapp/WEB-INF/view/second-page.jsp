<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<h2>Hello</h2>
<form:form action="findByCategory" method="get" modelAttribute="dish">
<br><br>
Menu
<br><br>
Category <form:select path="category">

         <form:option value="HOT" label="Hot" />
         <form:option value="DRINKS" label="Drinks" />
         <form:option value="SNACKS" label="Snacks" />
         <form:option value="DESSERTS" label="Desserts" />
         </form:select>

<input type="button" value="Balance" onclick="window.location.href = 'balance'"/>
<input type="submit" value="Ok" /></form:form>
</body>
</html>