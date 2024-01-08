<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3 : 로그인</title>
    <link href="/css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/js/loginform.js"></script>
</head>
<body>
    <sec:authorize access="permitAll()">
        <%@include file="header.jsp"%>
    </sec:authorize>

    <div class="main">
        <div class="container text-center">
            <div class="main-header">
                <h3 class="main-header-info">로그인</h3>
            </div>

            <div class="form-border-box">
                <form name="loginform" action="/login" method="post">
                    <div class="mb-3 text-box">
                        <input type="text" id="username" class="login" name="id" placeholder="아이디">
                    </div>
                    <div class="mb-3 text-box">
                        <input type="password" id="password" class="login" name="password" placeholder="비밀번호">
                    </div>

                    <div id="loginErrMsg">
                        <span id="errMsg">아이디 혹은 비밀번호가 일치하지 않습니다.<br>입력한 내용을 다시 확인해주세요.</span>
                    </div>

                    <div class="confirm-btn-box">
                        <button id="loginBtn" type="button" class="btn_l btn_top">로그인</button>
                        <a class="btn_l btn_gray" href="/joinform">회원가입</a>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</body>
</html>