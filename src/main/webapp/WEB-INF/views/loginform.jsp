<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3 : 로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="main">
        <div class="container text-center">
            <div class="main-header">
                <h3 class="main-header-info">로그인</h3>
            </div>

            <div class="form-border-box">
                <form id="" action="/login" method="post">
                     <div class="mb-3 text-box">
                        <input type="username" id="username" class="login" name="username" placeholder="아이디">
                     </div>
                    <div class="mb-3 text-box">
                        <input type="password" id="password" class="login" name="password" placeholder="비밀번호">
                    </div>

                    <div id="loginErrMsg">
                        <sec:authorize access="isAnonymous()">
                            <span class="errMsg">아이디 혹은 비밀번호가 일치하지 않습니다.</span>
                            <span class="errMsg">입력한 내용을 다시 확인해주세요.</span>
                        </sec:authorize>
                    </div>

                    <div class="confirm-btn-box">
                        <button id="loginBtn" class="btn_l">로그인</button>
                        <a class="btn_l btn_gray" href="/joinform">회원가입</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</body>
</html>