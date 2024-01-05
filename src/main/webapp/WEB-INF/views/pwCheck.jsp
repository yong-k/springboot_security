<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3 : 비밀번호 확인</title>
    <link href="/css/style.css" rel="stylesheet">
    <script>
        /**
         * [code]
         * -3 : 비밀번호가 일치하지 않습니다.
         * */
        let queryString = window.location.search;
        let urlParams = new URLSearchParams(queryString);
        let code = urlParams.get("code");

        if (code == -3)
            alert("비밀번호가 일치하지 않습니다.");
    </script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="main">
    <div class="container text-center">
        <div class="main-header">
            <h3 class="main-header-info">비밀번호 재확인</h3>
        </div>

        <div class="form-border-box">
            <form action="/user/checkpw" method="post">
                <div class="mb-3 text-box">
                    <input type="password" id="password" class="login" name="password" placeholder="비밀번호 입력">
                </div>
                <div class="confirm-btn-box">
                    <button class="btn_l btn_top">확인</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>