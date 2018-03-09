package com.papaya.core.validate.code;

import com.papaya.core.properties.PapayaSecurityProperties;
import com.papaya.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private AuthenticationFailureHandler authenticationFailureHandler;

    private PapayaSecurityProperties securityProperties;

    Map<String,ValidateCodeType> urlMap = new HashMap<String,ValidateCodeType>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    ValidateCodeProcesserHolder validateCodeProcesserHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        addUrlToMap(securityProperties.getValidateCode().getImageCode().getUrls(),ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getValidateCode().getSmsCode().getUrls(),ValidateCodeType.SMS);
    }

    public void addUrlToMap(String urls,ValidateCodeType validateCodeType){
        String[] urlArr = StringUtils.split(urls,",");
        for(String url :urlArr){
            urlMap.put(url,validateCodeType.IMAGE);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       ValidateCodeType validateCodeType = getValidateCodeType(request);
        if (validateCodeType!=null) {
            try {
                validateCodeProcesserHolder.findValidateCodeProcesser(validateCodeType).validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    public ValidateCodeType getValidateCodeType(HttpServletRequest request){
        ValidateCodeType result = null;
        if(!StringUtils.equals(request.getMethod(),"get")){
           Set<String> urls =  urlMap.keySet();
           for(String url :urls){
              if(pathMatcher.match(url,request.getRequestURI())){
                  result = urlMap.get(url);
              }
           }
        }
        return result;
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
