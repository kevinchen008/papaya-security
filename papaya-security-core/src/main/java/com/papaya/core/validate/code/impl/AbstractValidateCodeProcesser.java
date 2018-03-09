package com.papaya.core.validate.code.impl;

import com.papaya.core.validate.code.*;
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
    public Map<String,ValidateCodeGenerator> validateCodeGenerators;

    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void create(ServletWebRequest request) throws IOException {
        C validateCode = getValidateCode(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    /**
     * 生成验证码
     * @param request
     * @return
     */
    public C getValidateCode(ServletWebRequest request){
        String type =getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type+ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if(validateCodeGenerator==null){
            throw new ValidateCodeException("验证码生成器"+generatorName+"找不到");
        }
        return (C) validateCodeGenerator.generatorCode(request.getRequest());
    }

    /**
     *  获取验证码类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcesser");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 保存验证码
     * @param request
     * @param validateCode
     */
    public void save(ServletWebRequest request,C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PRE+getValidateCodeType(request).toString(),validateCode);
    }

    public abstract void send(ServletWebRequest request,C validateCode) throws IOException;


    /**
     * 校验验证码
     * @param request
     * @throws ServletRequestBindingException
     */
    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        C codeInSession = (C) sessionStrategy.getAttribute(request,SESSION_KEY_PRE+validateCodeType.toString());
        String codeInReq =  ServletRequestUtils.getStringParameter(request.getRequest(),validateCodeType.getParamNameOnValidate());

        if(StringUtils.isEmpty(codeInReq)){
            throw new ValidateCodeException("获取验证码不存在.");
        }

        if(StringUtils.isEmpty(codeInSession.getCode())){
            throw new ValidateCodeException("获取验证码不存在.");
        }

        if(codeInSession.isExpried()){
            throw new ValidateCodeException("验证码超时");
        }

        if(!StringUtils.endsWithIgnoreCase(codeInReq,codeInSession.getCode())){
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request,SESSION_KEY_PRE+ validateCodeType.toString());
    }

}
