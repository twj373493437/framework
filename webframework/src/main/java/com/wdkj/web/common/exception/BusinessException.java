package com.wdkj.web.common.exception;

/**
 * 业务异常， 默认不爬栈
 * @author : TianWenjian
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        this(message, null);
    }

    public BusinessException(String message, Throwable cause){
        this(message, cause, false);
    }

    public BusinessException(String message, Throwable cause, boolean writableStackTrace){
        super(message, cause, false, writableStackTrace);
    }
}
