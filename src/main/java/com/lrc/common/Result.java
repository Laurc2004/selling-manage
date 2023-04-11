package com.lrc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    // 省略getter和setter方法

    /**
     * 成功时的返回结果
     *
     * @param data 返回的数据
     * @param <T>  数据类型
     * @return 统一返回结果
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <T> Result<T> success(String message,T data){
        return new Result<>(200,message,data);
    }

    /**
     * 失败时的返回结果
     *
     * @param code    错误码
     * @param message 错误信息
     * @param <T>     数据类型
     * @return 统一返回结果
     */
    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }
}