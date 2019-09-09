package com.guotie.mdc.sys.bubase;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : LiuMingyao
 * @date : 2019/9/9 16:14
 * @description : 分页配置
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.guotie.mdc.sys.bubase.mapper.*")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}