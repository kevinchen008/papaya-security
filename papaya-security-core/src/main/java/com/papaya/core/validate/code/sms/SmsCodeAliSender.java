package com.papaya.core.validate.code.sms;

import com.papaya.core.validate.code.sms.SmsCodeSender;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeAliSender implements SmsCodeSender {
    @Override
    public void sendSms(String phone, String content) {
        System.out.println("发送者手机号:"+phone+"发送内容:"+content);
    }
}
