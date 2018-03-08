package com.papaya.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ImageCodeGenerator {

     ImageCode createImageCode(HttpServletRequest request);
}
