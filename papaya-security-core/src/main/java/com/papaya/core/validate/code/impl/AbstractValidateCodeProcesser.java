package com.papaya.core.validate.code.impl;

import com.papaya.core.validate.code.ValidateCode;
import com.papaya.core.validate.code.ValidateCodeGenerator;
import com.papaya.core.validate.code.ValidateCodeProcesser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/8 14:44
 * @Version: 1.0
 */
public abstract class AbstractValidateCodeProcesser<C extends ValidateCode> implements ValidateCodeProcesser {

    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGenerators;

    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void create(ServletWebRequest request) throws IOException {
        C validateCode = getValidateCode(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    public C getValidateCode(ServletWebRequest request){
        String type =getValidateType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type+"CodeGenerator");
        return (C) validateCodeGenerator.generatorCode(request.getRequest());
    }


    public String getValidateType(ServletWebRequest request){
        return  StringUtils.substringAfter(request.getRequest().getRequestURI(),"/");
    }

    public void save(ServletWebRequest request,C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PRE+getValidateType(request),validateCode);
    }

    public abstract void send(ServletWebRequest request,C validateCode) throws IOException;


    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        C sessionStrategyAttribute = (C) sessionStrategy.getAttribute(request,SESSION_KEY_PRE+getValidateType(request));
        ServletRequestUtils.getStringParameter(request.getRequest(),SESSION_KEY_PRE);

    }

}
