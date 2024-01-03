<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3</title>
</head>
<body>
    <h1>Join</h1>
    <form action="/join" method="post">
        <input type="text" name="name" placeholder="name"><br>
        <input type="text" name="username" placeholder="username"><br>
        <input type="password" name="password" placeholder="password"><br>
        <input type="text" name="email" placeholder="email"><br>
        <input type="text" name="address" placeholder="address"><br>
        <input type="text" name="phone" placeholder="phone"><br>
        <input type="text" name="website" placeholder="website"><br>
        <input type="text" name="company" placeholder="company"><br>
        <button>회원가입</button>
    </form>
</body>
</html>