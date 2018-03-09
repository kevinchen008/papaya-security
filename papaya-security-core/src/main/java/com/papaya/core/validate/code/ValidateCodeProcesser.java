package com.papaya.core.validate.code;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @Description: 验证码处理器
 * @Author: kevin.chen
 * @CreateDate: 2018/3/8 13:59
 * @Version: 1.0
 */
public interface ValidateCodeProcesser {

    String SESSION_KEY_PRE="SESSION_KEY_PRE";

    void create(ServletWebRequest request) throws IOException;

    void validate(ServletWebRequest request) throws ServletRequestBindingException;
}
