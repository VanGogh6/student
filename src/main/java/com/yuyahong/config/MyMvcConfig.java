package com.yuyahong.config;

import com.yuyahong.interceptor.LoginHandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
@Component
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")//拦截所有请求
                //放行的请求  , "**.css", "**.js", "**.jpg", "**.eot", "**.svg", "**.ttf", "**.woff", "**.woff2"
                .excludePathPatterns("/toLogin", "/login");
    }

//    /**
//     * 放行静态资源
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/fonts/");
//    }

    /**
     * 视图
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
    }
}