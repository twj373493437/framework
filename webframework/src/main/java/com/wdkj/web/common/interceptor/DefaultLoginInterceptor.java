package com.wdkj.web.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.wdkj.web.common.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author twj
 * @date 2018/9/19 16:22
 */
public class DefaultLoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(DefaultLoginInterceptor.class);
    public static final String SESSION_USER = "session_user";

    private String loginPagePath;

    public DefaultLoginInterceptor(String loginPagePath){
        this.loginPagePath = loginPagePath;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        log.debug("url:" + url);

        HttpSession session = request.getSession(false);
        if (session == null || session.isNew() || session.getAttribute(SESSION_USER) == null) {
            if (log.isDebugEnabled()) {
                log.debug("拒绝了请求" + url);
            }

            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equals("XMLHttpRequest")) {
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    writer.write(JSON.toJSONString(ResultUtils.fail("错误：")));
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");

                } catch (IOException e) {
                    log.error("写数据发送错误", e);
                } finally {
                    if (writer != null) {
                        writer.flush();
                        writer.close();
                    }
                }
                return false;
            }

            response.sendRedirect(request.getContextPath() + loginPagePath);
            return false;
        }

        return true;
    }

}
