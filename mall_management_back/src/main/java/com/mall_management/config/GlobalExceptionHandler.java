package com.mall_management.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.mall_management.utils.Res;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理Validated验证异常
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    public Res<String> bindExceptionHandler(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return Res.error(objectError.getDefaultMessage());
    }

    /**
     * 处理Validated验证异常
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Res<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        return Res.error(e.getMessage().split(",")[0].split(":")[1]);
    }


    // 捕获 NotLoginException
    @ExceptionHandler(NotLoginException.class)
    public Res<String> handleNotLoginException(NotLoginException e) {
        return Res.error("token校验失败");
    }


    @ExceptionHandler({NotPermissionException.class})
    public  Res<String> notPermissionExceptionHandler(NotPermissionException e) {
        return Res.error("暂无权限");
    }

}