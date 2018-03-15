package com.papaya.config;




/*@Configuration
@MapperScan("com.papaya.mapper*")*/
public class MybatisPlusConfig {

  /*  *//**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     *//*
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    *//**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     *//*
    @Bean
    public PaginationInterceptor paginationInterceptor() {
       return  new PaginationInterceptor();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MyMetaObjectHandler();
    }

    *//**
     * 注入主键生成器
     *//*
    @Bean
    public IKeyGenerator keyGenerator(){
        return new H2KeyGenerator();
    }

    *//**
     * 注入sql注入器
     *//*
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }*/

    /**
     * 注入乐观插件
     * @return
     */
    /*@Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }*/
}
