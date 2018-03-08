package com.papaya.core.validate.code.sms;

import com.papaya.core.validate.code.impl.AbstractValidateCodeProcesser;
import com.papaya.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

@Component("smsCodeProcesser")
public class SmsCodeProcesser extends AbstractValidateCodeProcesser<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) throws IOException {
        smsCodeSender.sendSms("",validateCode.getCode());
    }
}
