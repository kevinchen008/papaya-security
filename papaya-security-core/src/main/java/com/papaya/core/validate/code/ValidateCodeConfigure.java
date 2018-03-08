package com.papaya.core.validate.code;

import com.papaya.core.properties.PapayaSecurityProperties;
import com.papaya.core.validate.code.image.ImageCodeDefaultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConfigure {

    @Autowired
    private PapayaSecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeDefaultGenerator(){
        ImageCodeDefaultGenerator imageCodeDefaultGenerator = new ImageCodeDefaultGenerator();
        imageCodeDefaultGenerator.setSecurityProperties(securityProperties);
        return imageCodeDefaultGenerator;
    }

}
