<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>
                        <a href="${request.contextPath}/finalEAMProject/user?action=edit&id=${user.id}">Edit</a>
                        <a href="${request.contextPath}/finalEAMProject/user?action=delete&id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="${request.contextPath}/user?action=edit">Add New User</a>
</body>
</html>
