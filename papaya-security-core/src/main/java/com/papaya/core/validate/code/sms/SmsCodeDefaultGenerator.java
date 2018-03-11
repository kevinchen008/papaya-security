package com.papaya.core.validate.code.sms;

import com.papaya.core.validate.code.ValidateCode;
import com.papaya.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/8 10:00
 * @Version: 1.0
 */

@Component("smsValidateCodeGenerator")
public class SmsCodeDefaultGenerator implements ValidateCodeGenerator<ValidateCode> {

    @Override
    public ValidateCode generatorCode(HttpServletRequest request) {

        return null;
    }
}
