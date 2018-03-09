package com.papaya.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateCodeController {


    @Autowired
    private ValidateCodeProcesserHolder validateCodeProcesserHolder;

    @GetMapping("/code/{type}")
    public void createImageCode(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        validateCodeProcesserHolder.findValidateCodeProcesser(type).create(new ServletWebRequest(request));
    }

}
