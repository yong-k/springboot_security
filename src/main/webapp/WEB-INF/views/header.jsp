<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

    <header class="p-3">
        <div class="container">
          <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
              <li class="nav-item"><a href="/" class="nav-link link-body-emphasis px-2">MAIN_LOGO</a></li>
            </ul>

            <sec:authorize access="isAnonymous()">
                <ul class="nav">
                  <li class="nav-item"><a href="/loginform" class="nav-link link-body-emphasis px-2">로그인</a></li>
                  <li class="nav-item"><a href="/joinform" class="nav-link link-body-emphasis px-2">회원가입</a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ul class="nav">
                  <li class="nav-item"><span class="nav-link link-body-emphasis px-2"><sec:authentication property="principal.username"/>님</span></li>
                  <li class="nav-item"><a href="/user/info" class="nav-link link-body-emphasis px-2">마이페이지</a></li>
                  <li class="nav-item"><a href="/logout" class="nav-link link-body-emphasis px-2">로그아웃</a></li>
                </ul>
            </sec:authorize>
          </div>
        </div>
      </header>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>