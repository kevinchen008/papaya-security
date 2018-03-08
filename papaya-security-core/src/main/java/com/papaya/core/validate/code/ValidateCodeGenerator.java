package com.papaya.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator<T> {
     T generatorCode(HttpServletRequest request);
}
