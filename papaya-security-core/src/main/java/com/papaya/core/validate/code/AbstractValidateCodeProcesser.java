package com.papaya.core.validate.code;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

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
    public void create(ServletWebRequest request) {
        C validateCode = getValidateCode(request);

    }

    public C getValidateCode(ServletWebRequest request){
        String type = StringUtils.substringAfter(request.getRequest().getRequestURI(),"/");
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type+"CodeGenerator");
        return (C) validateCodeGenerator.generatorCode(request.getRequest());
    }

    public void save(ServletWebRequest request,C validateCode){

    }

    public abstract void send(ServletWebRequest request,C validateCode);

}
