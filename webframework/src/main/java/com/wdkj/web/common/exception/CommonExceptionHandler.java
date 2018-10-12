package com.wdkj.web.common.exception;

import com.alibaba.fastjson.JSON;
import com.wdkj.utils.string.MyStringUtils;
import com.wdkj.web.common.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CommonExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    private String errorUrl = "/error";

    public CommonExceptionHandler() {

    }

    public CommonExceptionHandler(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object arg2, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();

        String eMessage = ex.getMessage();

        String params = MyStringUtils.from(request.getParameterMap());
        log.error("Spring MVC 捕捉到了一个异常: url:" + request.getRequestURL() + lineSeparator()
                + " params: " + params, ex);

        String res = "";

        // get 请求的URL， 分析后缀判断ajax
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equals("XMLHttpRequest")) {

            res = JSON.toJSONString(ResultUtils.fail("错误：" + eMessage));

            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.write(res);

            } catch (IOException e) {
                e.printStackTrace();
                model.put("error", e.getMessage());
                return new ModelAndView("error/error", model);
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }

            return null;
        } else {
            //model.put("error", trace);
            return new ModelAndView("redirect:" + errorUrl);
        }
    }

    private String lineSeparator() {
        String s = System.getProperty("line.separator");
        if (s != null) {
            return " " + s + " ";
        } else {
            return " \\r\\n ";
        }
    }
}
