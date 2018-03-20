package com.papaya.security.browser;

import com.papaya.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.papaya.core.properties.PapayaSecurityProperties;
import com.papaya.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PapayaSecurityProperties papayaSecurityProperties;

    @Autowired
    private AuthenticationSuccessHandler papayaAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler papayaAuthenticationFailureHandler;

    @Autowired
    private  ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

       // http.formLogin().and().authorizeRequests().anyRequest().authenticated();

        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form1")
                .successHandler(papayaAuthenticationSuccessHandler)
                .failureHandler(papayaAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require","/authentication/mobile","/authentication/form1","/boot/qq","/demo-signup.html",papayaSecurityProperties.getBrowser().getLoginPage(),"/code/image","/user/regist")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer);
    }
}
