package com.papaya.core.social.qq;

import com.papaya.core.properties.PapayaSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/3/15 11:02
 * @Version: 1.0
 */
@Configuration
@EnableSocial
public class SocialConfigure extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PapayaSecurityProperties papayaSecurityProperties;

    @Bean
    public PapayaSocialConfigure papayaSocialConfigure(){
        PapayaSocialConfigure papayaSocialConfigure = new PapayaSocialConfigure();
        papayaSocialConfigure.setProcessUrl(papayaSecurityProperties.getSocial().getQq().getFilterProcessesUrl());
        return papayaSocialConfigure;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
    }
}
