package com.papaya.core.validate.code;

import com.papaya.core.properties.PapayaSecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private AuthenticationFailureHandler authenticationFailureHandler;


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private PapayaSecurityProperties securityProperties;

    Set<String> urls = new HashSet<>();


    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        String[] arrs = StringUtils.split( securityProperties.getValidateCode().getImageCode().getUrls(),",");
        CollectionUtils.mergeArrayIntoCollection(arrs,urls);
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for(String url : urls){
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode imageCode = (ImageCode)sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
        String CodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");

        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public PapayaSecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(PapayaSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
