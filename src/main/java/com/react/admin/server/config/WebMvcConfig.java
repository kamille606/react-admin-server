package com.react.admin.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.react.admin.server.constant.BaseConst.PICTURE_PATH;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //所有的当前站点的请求地址,都支持跨域访问。
                .allowedOriginPatterns("*")
                .allowCredentials(true) //是否支持跨域用户凭证
                .allowedMethods(ORIGINS) //当前站点支持的跨域请求类型是什么
                .maxAge(3600); //超时时长设置为1小时。 时间单位是秒。
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + PICTURE_PATH);
    }
}
