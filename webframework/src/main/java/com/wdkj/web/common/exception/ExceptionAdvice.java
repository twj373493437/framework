package com.wdkj.web.common.exception;

import com.alibaba.fastjson.JSON;
import com.wdkj.utils.string.MyStringUtils;
import com.wdkj.web.common.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author twj
 * @date 2018/7/5 18:13
 */
//@ControllerAdvice
@Deprecated
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public Object businessExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex){

        String eMessage = ex.getMessage();

        String params = MyStringUtils.stringArrayMapToString(request.getParameterMap());
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
                log.error("返回ajax错误信息时发生错误：", e);
                //model.put("error", e.getMessage());
                return new ModelAndView("redirect:error/error.jsp");
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }

            return null;
        } else {
            //model.put("error", trace);
            return new ModelAndView("error");
        }
    }

    private String lineSeparator(){
        String s = System.getProperty("line.separator");
        if (s != null){
            return " " + s + " ";
        }else {
            return " \\r\\n ";
        }
    }
}
