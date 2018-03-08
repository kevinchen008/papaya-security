package com.papaya.core.validate.code;

import com.papaya.core.properties.PapayaSecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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


    @Autowired
    private Map<String,ValidateCodeProcesser> validateCodeProcessers;


    @GetMapping("/code/{type}")
    public void createImageCode(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
       ValidateCodeProcesser validateCodeProcesser =  validateCodeProcessers.get(type+"CodeProcesser");
       validateCodeProcesser.create(new ServletWebRequest(request));
    }

}
