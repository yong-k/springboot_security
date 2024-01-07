<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>web3 : 개인정보수정</title>
    <link href="/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/js/updateform.js"></script>
</head>
<body>
    <sec:authorize access="permitAll()">
        <%@include file="header.jsp"%>
    </sec:authorize>

    <div class="main">
        <div class="container text-center">
            <div class="main-header">
                <h3 class="main-header-info">개인 정보 수정</h3>
            </div>

            <div id="join-box" class="form-border-box">
                <form id="updateform" name="saveForm" action="/user/update" method="post">
                    <div class="mb-3 row">
                        <label for="name" class="col-sm-3 col-form-label">이름</label>
                        <div class="col">
                            <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="username" class="col-sm-3 col-form-label">아이디<span class="form-required">*</span></label>
                        <div class="col">
                            <input type="username" class="form-control" id="username" name="username" value="${user.username}" readonly>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="password" class="col-sm-3 col-form-label">현재 비밀번호</label>
                        <div class="col">
                            <input type="password" class="form-control" id="nowPassword">
                            <div class="errMsg invalid-feedback" id="nowPasswordErrMsg">현재 비밀번호를 확인해주세요.</div>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="password" class="col-sm-3 col-form-label">새 비밀번호</label>
                        <div class="col">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="password-check" class="col-sm-3 col-form-label">새 비밀번호<br>확인</label>
                        <div class="col">
                            <input type="password" class="form-control" id="password-check">
                            <div class="errMsg invalid-feedback" id="password-checkErrMsg">비밀번호가 일치하지 않습니다.</div>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="username" class="col-sm-3 col-form-label">이메일<span class="form-required">*</span></label>
                        <div class="col">
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="ex) abc123@naver.com">
                            <div class="errMsg invalid-feedback" id="emailErrMsg">사용할 수 없는 이메일입니다.</div>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="address" class="col-sm-3 col-form-label">주소</label>
                        <div class="col">
                            <input type="address" class="form-control" id="address" name="address" value="${user.address}">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="phone" class="col-sm-3 col-form-label">휴대폰</label>
                        <div class="col">
                            <input type="phone" class="form-control" id="phone" name="phone" value="${user.phone}" oninput="oninputPhone(this)" maxlength="13" placeholder="ex) 01012345678">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="company" class="col-sm-3 col-form-label">회사</label>
                        <div class="col">
                            <input type="company" class="form-control" id="company" name="company" value="${user.company}">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="website" class="col-sm-3 col-form-label">사이트</label>
                        <div class="col">
                            <input type="text" class="form-control" id="website" name="website" value="${user.website}" placeholder="ex) www.naver.com">
                        </div>
                    </div>

                    <div class="userform-btn-box">
                        <button id="saveBtn" type="button" class="btn_l">수정</button>
                        <div class="errMsg invalid-feedback" id="requiredErrMsg">닉네임, 비밀번호, 이메일은 필수 입력 항목입니다.</div>
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