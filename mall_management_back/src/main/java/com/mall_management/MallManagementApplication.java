package com.mall_management;

import cn.dev33.satoken.SaManager;
import io.lettuce.core.dynamic.annotation.Value;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.mall_management.dao.mapper")
public class MallManagementApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(MallManagementApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}


}
