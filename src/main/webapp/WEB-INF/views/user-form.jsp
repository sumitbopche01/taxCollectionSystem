<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h1>User Form</h1>
    <form action="${request.contextPath}/user" method="post">
        <input type="hidden" name="action" value="save"/>
        <input type="hidden" name="id" value="${user.id}"/>
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" value="${user.username}"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" value="${user.password}"/></td>
            </tr>
        </table>
        <br/>
        <input type="submit" value="Save"/>
        <a href="${request.contextPath}/user?action=list">Cancel</a>
    </form>
</body>
</html>
