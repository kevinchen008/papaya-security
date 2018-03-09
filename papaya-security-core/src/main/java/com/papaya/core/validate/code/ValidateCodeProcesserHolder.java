package com.papaya.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/9 12:40
 * @Version: 1.0
 */
@Component
public class ValidateCodeProcesserHolder {

    @Autowired
    private Map<String,ValidateCodeProcesser> validateCodeProcessers;

    public ValidateCodeProcesser findValidateCodeProcesser(ValidateCodeType type){
        return this.findValidateCodeProcesser(type.toString());
    }

    public ValidateCodeProcesser findValidateCodeProcesser(String type){
        String name = type.toLowerCase()+ValidateCodeProcesser.class.getSimpleName();
        ValidateCodeProcesser processer = validateCodeProcessers.get(name);
        if(processer==null){
            throw new ValidateCodeException("验证码处理器"+name+"不存在");
        }
        return processer;
    }
}
