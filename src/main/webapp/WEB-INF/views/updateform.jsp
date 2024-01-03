<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3</title>
</head>
<body>
    <h1>userInfo update</h1>
    <form action="/user/update" method="post">
        <input type="text" name="name" value="${user.name}"><br>
        <input type="text" name="username" value="${user.username}"><br>
        <input type="password" name="password"><br>
        <input type="text" name="email" value="${user.email}"><br>
        <input type="text" name="address" value="${user.address}"><br>
        <input type="text" name="phone" value="${user.phone}"><br>
        <input type="text" name="website" value="${user.company}"><br>
        <input type="text" name="company" value="${user.website}"><br>
        <button>수정</button>
    </form>
</body>
</html>