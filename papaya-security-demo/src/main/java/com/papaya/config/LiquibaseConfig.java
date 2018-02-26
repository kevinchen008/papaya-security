package com.papaya.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/2/26 16:52
 * @Version: 1.0
 */
@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts("development,test,production");
        liquibase.setShouldRun(true);
        return liquibase;
    }
}
