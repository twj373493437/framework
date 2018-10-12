package com.wdkj.ftest.config;

import com.wdkj.web.common.exception.CommonExceptionHandler;
import com.wdkj.web.common.interceptor.DefaultLoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcCfg implements WebMvcConfigurer {

    public static final String[] URL_MATCH = {"/**"};
    public static final String[] URL_EXCLUDE = {"/sys/login/login"};

    @Value("${sys.loginViewUrl}")
    String loginPage;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultLoginInterceptor(loginPage)).addPathPatterns(URL_MATCH).excludePathPatterns(URL_EXCLUDE);
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new CommonExceptionHandler());
    }

    //解决跨越问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://192.168.1.97")
                .allowedMethods("GET", "POST")
                .allowCredentials(false).maxAge(3600);
    }
}
