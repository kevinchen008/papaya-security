package com.papaya.core.validate.code.sms;

public interface SmsCodeSender {
    public void sendSms(String phone,String content);
}
