<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Register Form</h1>
<form:form action="save" method="post" modelAttribute="user">
			<table style="with: 50%">
				<tr>
					<td>First Name</td>
					<td><form:input path="name"/></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><form:input path="surname"/></td>
				</tr>
				<tr>
                    <td>Phone</td>
                	<td><form:input path="phone"/></td>
                </tr>

                <tr>
                    <td>Role</td>
                    <td>User <form:radiobutton path="role" value="USER"/></td>
                </tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email"/></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>


			<input type="submit" value="Ok" /></form:form>
</body>
</html>