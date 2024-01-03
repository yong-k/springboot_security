package com.security.web3.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        /**
         * [loginErrCode]
         * -1: 아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.
         * -2: 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.
         * -99: 예상치 못한 오류가 발생했습니다.
         * */
        int loginErrCode;

        if (exception instanceof BadCredentialsException)
            loginErrCode = -1;
        else if (exception instanceof InternalAuthenticationServiceException)
            loginErrCode = -2;
        else
            loginErrCode = -99;

        setDefaultFailureUrl("/loginform?code=" + loginErrCode);
        super.onAuthenticationFailure(request, response, exception);
    }
}
