package com.papaya.core.validate.code;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/8 13:22
 * @Version: 1.0
 */
public class SmsCodeFilter extends OncePerRequestFilter {

    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            validate(new ServletWebRequest(request));
        } catch (ValidateCodeException e) {
            authenticationFailureHandler.onAuthenticationFailure(request,response,e);
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ValidateCode smsCode =  (ValidateCode)sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY_SMS_CODE);
        String smsCodeInRequest =   ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"smsCode");

        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY_SMS_CODE);
    }
}
