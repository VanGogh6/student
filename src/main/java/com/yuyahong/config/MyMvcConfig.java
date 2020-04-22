package com.yuyahong.config;

import com.yuyahong.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义配置类
 * 与静态资源放行
 * 页面跳转相关的配置
 *
 * @author yuyahong
 * @date 2020/4/21 0021 20:22
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/user/**")//拦截所有请求
                //放行的请求
                .excludePathPatterns("/toLogin", "/login");
    }

    /**
     * 视图
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 静态资源前加static
         */
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        //静态资源前不加静态资源前加static
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
//        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}