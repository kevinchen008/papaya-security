package com.papaya.core;

import com.papaya.core.properties.PapayaSecurityProperties;
import com.papaya.core.social.qq.PapayaSocialConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableConfigurationProperties(PapayaSecurityProperties.class)
public class SecurityCoreConfig {


    @Autowired
    private PapayaSecurityProperties papayaSecurityProperties;

    @Bean
    public SpringSocialConfigurer papayaSocialConfigure(){
        PapayaSocialConfigure papayaSocialConfigure = new PapayaSocialConfigure();
        papayaSocialConfigure.setProcessUrl(papayaSecurityProperties.getSocial().getQq().getFilterProcessesUrl());
        papayaSocialConfigure.signupUrl(papayaSecurityProperties.getBrowser().getSignUpPage());
        return papayaSocialConfigure;
    }

}
