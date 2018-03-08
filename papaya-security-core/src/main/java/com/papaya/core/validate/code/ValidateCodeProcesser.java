package com.papaya.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/8 13:59
 * @Version: 1.0
 */
public interface ValidateCodeProcesser {

    public static final String SESSION_KEY_PRE="SESSION_KEY_PRE";

    void create(ServletWebRequest request);
}
