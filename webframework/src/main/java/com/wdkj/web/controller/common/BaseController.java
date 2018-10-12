package com.wdkj.web.controller.common;

import com.wdkj.utils.date.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author twj
 * @date 2018/9/19 13:51
 */
public class BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    // 绑定数据转换器
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 从request参数中获取一个bean
     *
     * @param clazz
     * @param request
     * @return
     *
     */
//    protected Object getObjectFromRequest(Class<?> clazz, HttpServletRequest request) {
////        try {
////            Map<String, String[]> map = request.getParameterMap();
////            Map<String, String> temp = new HashMap<>();
////            Iterator<Map.Entry<String, String[]>> entries = map.entrySet().iterator();
////            while (entries.hasNext()) {
////                Map.Entry<String, String[]> entry = entries.next();
////                String[] values = entry.getValue();
////                String name = entry.getKey();
////
////                if (values != null && values.length > 0) {
////                    if (values.length > 1) {
////                        String value = "";
////                        for (String s : values) {
////                            value += (s + ";");
////                        }
////                        temp.put(name, value);
////                    } else {
////                        temp.put(name, values[0]);
////                    }
////                }
////            }
////
////            return BeanUtils.getBeanFromMap(clazz, temp);
////        }catch (Exception e) {
////            //log.error("参数获取失败", e);
////            // return null;
////            throw new RuntimeException("参数获取失败:" + e.getMessage(), e);
////        }
//
//    }

    /**获取本地路径
     * @param path
     * @return
     */
    public String getLocalPath(HttpServletRequest request, String path){
        ServletContext sc = request.getSession().getServletContext();
        return sc.getRealPath(path);
    }

    /**
     * 获取服务器的网络路径
     *
     * @return
     */
    public String getServerPath(HttpServletRequest request) {
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
        return path;
    }

    public static class DateEditor extends PropertyEditorSupport {

        private static Logger log = LoggerFactory.getLogger(DateEditor.class);

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Date date = null;

            if (text == null || text.length() == 0 || text.trim().equals("")) {
                setValue(date);
                return;
            }

            try {
                date = DateTimeUtils.parseDateTime(text);
            } catch (ParseException e) {
                try {
                    date = DateTimeUtils.parseDate(text);
                } catch (ParseException e1) {
                    throw new RuntimeException("转换日期错误:" + text, e1);
                }
            }
            setValue(date);
        }
    }
}
