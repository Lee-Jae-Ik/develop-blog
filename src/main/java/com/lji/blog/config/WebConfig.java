package com.lji.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebConfig
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/03/01
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        RedirectViewControllerRegistration redirectViewControllerRegistration = registry.addRedirectViewController("/", "swagger-ui.html");
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
