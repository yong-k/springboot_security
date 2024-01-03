<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3</title>
</head>
<body>
    <h1>userInfo</h1>
    <h3>${user.name}</h3>
    <h3>${user.username}</h3>
    <h3>${user.email}</h3>
    <h3>${user.address}</h3>
    <h3>${user.phone}</h3>
    <h3>${user.company}</h3>
    <h3>${user.website}</h3>

    <a href="/user/updateform">회원정보수정</a>
    <a href="/user/delete">회원탈퇴</a>
</body>
</html>