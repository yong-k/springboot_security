<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3 : 회원정보</title>
    <link href="/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
    <sec:authorize access="permitAll()">
        <%@include file="header.jsp"%>
    </sec:authorize>

    <div class="main">
        <div class="container text-center">
            <div class="main-header">
                <h3 class="main-header-info">회원정보</h3>
            </div>

            <div id="join-box" class="form-border-box">
                <form id="joinform" name="saveForm" action="/join" method="post">
                    <div class="mb-3 row">
                        <label for="name" class="col-sm-3 col-form-label">이름</label>
                        <div class="col">
                            <input type="text" class="form-control" id="name" name="name" value="${user.name}" disabled>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="username" class="col-sm-3 col-form-label">아이디</label>
                        <div class="col">
                            <input type="username" class="form-control" id="username" name="username" value="${user.username}" disabled>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="username" class="col-sm-3 col-form-label">이메일</label>
                        <div class="col">
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}" disabled>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="address" class="col-sm-3 col-form-label">주소</label>
                        <div class="col">
                            <input type="address" class="form-control" id="address" name="address" value="${user.address}" disabled>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="phone" class="col-sm-3 col-form-label">휴대폰</label>
                        <div class="col">
                            <input type="phone" class="form-control" id="phone" name="phone" value="${user.phone}" disabled>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="company" class="col-sm-3 col-form-label">회사</label>
                        <div class="col">
                            <input type="company" class="form-control" id="company" name="company" value="${user.company}" disabled>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="website" class="col-sm-3 col-form-label">사이트</label>
                        <div class="col">
                            <input type="text" class="form-control" id="website" name="website" value="${user.website}" disabled>
                        </div>
                    </div>

                    <div class="userform-btn-box">
                        <a id="updateformBtn" class="btn_l btn_top" href="/user/pwcheckform">개인 정보 수정</a>
                        <a class="btn_l btn_gray" href="/user/withdrawform">회원탈퇴</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <sec:authorize access="permitAll()">
        <%@include file="footer.jsp"%>
    </sec:authorize>
</body>
</html>