package com.mall_management.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // 自动生成常用的 getter、setter、toString
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Res<T> {

    private Integer code;
    private String message;
    private T  data = null;

    /**
     * 成功
     * @return
     */

    public static <T> Res<T> success() {
        return success(null, "请求成功");
    }
    public static <T> Res<T> success(T data) {
        return success(data, "请求成功");
    }

    public static <T> Res<T> success(T data, String message) {
        return new Res<>(200, message, data);
    }

    /**
     * 错误
     * @return
     */
    /**
     * Error response with default code 500 and message "请求失败".
     */
    public static <T> Res<T> error() {
        return error(500, "请求失败");
    }

    /**
     * Error response with custom code and default message "请求失败".
     */
    public static <T> Res<T> error(Integer code) {
        return error(code, "请求失败");
    }

    /**
     * Error response with default code 500 and custom message.
     */
    public static <T> Res<T> error(String message) {
        return error(500, message);
    }

    /**
     * Error response with custom code and message.
     */
    public static <T> Res<T> error(Integer code, String message) {
        return new Res<>(code, message, null);
    }
}
