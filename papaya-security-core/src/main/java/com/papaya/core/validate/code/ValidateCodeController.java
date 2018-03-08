package com.papaya.core.validate.code;

import com.papaya.core.properties.PapayaSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    public static final String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private PapayaSecurityProperties securityProperties;

    @Autowired
    private ValidateCodeGenerator imageCodeDefaultGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imggeCode = (ImageCode) imageCodeDefaultGenerator.generatorCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_IMAGE_CODE,imggeCode);
        ImageIO.write(imggeCode.getImage(),"JPEG",response.getOutputStream());

    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ValidateCode smsCode = (ValidateCode) smsCodeGenerator.generatorCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_SMS_CODE,smsCode);
    }


    public static void main(String[] args){
        System.out.println(ValidateCodeController.class.getSimpleName());
    }

}
