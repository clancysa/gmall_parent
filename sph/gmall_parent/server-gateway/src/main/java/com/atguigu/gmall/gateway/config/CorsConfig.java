package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;


import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;


/**
 * @author: y
 * @date: 2023/7/24 21:25
 * @description:
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置允许访问的网络
        corsConfiguration.setAllowCredentials(true);//设置是否从服务器获取cookie
        corsConfiguration.addAllowedMethod("*");//设置请求方法
        corsConfiguration.addAllowedHeader("*");//设置请求头信息

        //配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);

        //过滤器对象
        return new CorsWebFilter(configurationSource);
    }
}
