package com.movie.phase12.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class Webconfig  extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler(
                    "/webjars/**",
                    "/img/**",
                    "/static/css/**",
                    "/static/js/**")
                    .addResourceLocations(
                            "classpath:/META-INF/resources/webjars/",
                            "classpath:/static/images/",
                            "classpath:/static/css/",
                            "classpath:/static/js/");
        }

    }