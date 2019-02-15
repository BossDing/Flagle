package com.hrabbit.admin.config.web;

import com.hrabbit.admin.config.web.view.HrabbitErrorView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置中心
 * @Auther: hrabbit
 * @Date: 2018-12-17 6:45 PM
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 默认错误页面，返回json
     */
    @Bean("/error")
    public HrabbitErrorView error() {
        return new HrabbitErrorView();
    }

}