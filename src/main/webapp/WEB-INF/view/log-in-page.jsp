<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Log in</h1>
<form:form action="checkUser" method="post" modelAttribute="user">
			<table style="with: 50%">
				<tr>
					<td>Email</td>
					<td><form:input path="email"/></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>


			<input type="submit" value="Ok" />
			</form:form>
</body>
</html>

