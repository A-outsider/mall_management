package com.mall_management.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 路由拦截器
        registry.addInterceptor(new SaInterceptor((handler) -> {

            // 登录认证：拦截所有请求，排除 /login 用于开放登录
            SaRouter.match("/**")
                    .notMatch("/user/login")
                    .check(r -> StpUtil.checkLogin());

            // 权限认证：匹配restful风格路由、多个条件一起使用

            // 用户
            SaRouter.match(SaHttpMethod.GET).match("/user/list" , r->StpUtil.checkPermission("user:query"));
            SaRouter.match(SaHttpMethod.POST).match("/user" , r->StpUtil.checkPermission("user:add"));
            SaRouter.match(SaHttpMethod.PUT).match("/user/{id}" , r->StpUtil.checkPermission("user:update"));
            SaRouter.match(SaHttpMethod.DELETE).match("/user/{id}" , r->StpUtil.checkPermission("user:delete"));

            // 角色
            SaRouter.match(SaHttpMethod.GET).match("/role/list" , r->StpUtil.checkPermission("role:query"));
            SaRouter.match(SaHttpMethod.POST).match("/role" , r->StpUtil.checkPermission("role:add"));
            SaRouter.match(SaHttpMethod.PUT).match("/role/{id}" , r->StpUtil.checkPermission("role:update"));
            SaRouter.match(SaHttpMethod.DELETE).match("/role/{id}" , r->StpUtil.checkPermission("role:delete"));

            // 菜单
            SaRouter.match(SaHttpMethod.GET).match("/menu/list" , r->StpUtil.checkPermission("menu:query"));
            SaRouter.match(SaHttpMethod.POST).match("/menu" , r->StpUtil.checkPermission("menu:add"));
            SaRouter.match(SaHttpMethod.PUT).match("/menu/{id}" , r->StpUtil.checkPermission("menu:update"));
            SaRouter.match(SaHttpMethod.DELETE).match("/menu/{id}" , r->StpUtil.checkPermission("menu:delete"));

            // 部门
            SaRouter.match(SaHttpMethod.GET).match("/department/list" , r->StpUtil.checkPermission("department:query"));
            SaRouter.match(SaHttpMethod.POST).match("/department" , r->StpUtil.checkPermission("department:add"));
            SaRouter.match(SaHttpMethod.PUT).match("/department/{id}" , r->StpUtil.checkPermission("department:update"));
            SaRouter.match(SaHttpMethod.DELETE).match("/department/{id}" , r->StpUtil.checkPermission("department:delete"));




      })).addPathPatterns("/**");
    }
}
