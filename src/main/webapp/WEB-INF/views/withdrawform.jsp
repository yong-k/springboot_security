<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3 : 회원탈퇴</title>
    <link href="/css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/js/withdrawform.js"></script>
</head>
<body>
    <sec:authorize access="permitAll()">
        <%@include file="header.jsp"%>
    </sec:authorize>

    <div class="main">
        <div class="container text-center">
            <div class="main-header">
                <h3 class="main-header-info">회원탈퇴</h3>
            </div>
            <p id="withdraw-info">
                탈퇴 안내 문구1<br>
                탈퇴 안내 문구2<br>
                탈퇴 안내 문구3<br>
            </p>
            <div class="form-border-box">
                <form action="/user/delete" method="post">
                    <div class="mb-3 text-box">
                        <input type="password" id="password" class="login" name="password" placeholder="비밀번호 입력">
                    </div>
                    <div class="confirm-btn-box">
                        <button class="btn_l btn_top">탈퇴</button>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</body>
</html>