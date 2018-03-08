package com.papaya.security.browser;

import com.papaya.core.validate.code.ImageCode;
import com.papaya.core.validate.code.ImageCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("imageCodeDefaultGenerator")
public class ImageCodeTopGenerator implements ImageCodeGenerator {
    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        System.out.println("高级验证码生成");
        return null;
    }
}
