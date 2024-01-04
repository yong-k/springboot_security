package com.security.web3.security;

import com.security.web3.consts.ResultCode;
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
        int errCode;

        if (exception instanceof BadCredentialsException)
            errCode = ResultCode.SECURITY_BAD_CREDENTIALS.value();
        else if (exception instanceof InternalAuthenticationServiceException)
            errCode = ResultCode.SECURITY_INTERNAL_AUTHENTICATION_SERVICE.value();
        else
            errCode = ResultCode.UNKNOWN_ERROR.value();

        setDefaultFailureUrl("/loginform?code=" + errCode);
        super.onAuthenticationFailure(request, response, exception);
    }
}
