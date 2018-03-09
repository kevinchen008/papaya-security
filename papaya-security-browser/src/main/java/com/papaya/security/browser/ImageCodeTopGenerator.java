package com.papaya.security.browser;

import com.papaya.core.validate.code.image.ImageCode;
import com.papaya.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*@Component("imageValidateCodeGenerator")*/
public class ImageCodeTopGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generatorCode(HttpServletRequest request) {
        System.out.println("高级验证码生成");
        return null;
    }
}
