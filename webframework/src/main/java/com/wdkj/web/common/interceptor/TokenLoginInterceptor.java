package com.wdkj.web.common.interceptor;

/**
 * 基于token的登录验证器
 * 推荐采用这种方式  后台做无状态API 可同时适用于，手机端，浏览器端
 * 为了提高性能， 需要依赖Redis 或者 其他缓存
 * 在焕春中 缓存 用户信息 token作为key
 * @author twj
 * @date 2018/9/19 20:25
 */
public class TokenLoginInterceptor {
}
