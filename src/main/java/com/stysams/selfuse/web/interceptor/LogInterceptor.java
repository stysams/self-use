package com.dsfa.platform.starter.web.interceptor;

import com.dsfa.platform.sdk.common.kit.StrKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @ClassName LogInterceptor
 * @Author 文进
 * @Date 2020/7/16 下午6:43
 * @Version 1.0.0
 * @Description TODO
 **/
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final int maxOutputLengthOfParaValue = 1024;

    private final ThreadLocal<Common> startThreadLocal = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //非接口请求
            return true;
        }
        String uuid = UUID.randomUUID().toString();
        startThreadLocal.set(new Common(System.currentTimeMillis(), uuid));
        ThreadContext.put("_log_id",uuid);
        String methodName = ((HandlerMethod) handler).getMethod().getName();
        Class controller = ((HandlerMethod) handler).getBean().getClass();
        String queryStr = request.getQueryString();
        StringBuffer sb = new StringBuffer("----------------------\tDSF-SPRINGBOOT " + uuid + " start\t--------------------\n\n");
        sb.append("\tUrl         : " + request.getRequestURL().toString());
        if (StrKit.isNotBlank(queryStr)) {
            sb.append("?").append(queryStr);
        }
        sb.append("\n")
                .append("\tController  : ").append(controller.getName()).append(".(").append(controller.getSimpleName()).append(".java:1)").append("\n")
                .append("\tMethod      : " + methodName).append("\n");
        Enumeration<String> e = request.getParameterNames();
        if (e.hasMoreElements()) {
            sb.append("\tParameter   : ");
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    sb.append(name).append("=");
                    if (values[0] != null && values[0].length() > maxOutputLengthOfParaValue) {
                        sb.append(values[0], 0, maxOutputLengthOfParaValue).append("...");
                    } else {
                        sb.append(values[0]);
                    }
                } else {
                    sb.append(name).append("[]={");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
                sb.append("  ");
            }
            sb.append("\n");
        }
        log.debug(sb.toString());
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //非接口请求
            return;
        }
        Common common = startThreadLocal.get();
        if(common != null) {
            log.debug("总执行时间" + (System.currentTimeMillis() - common.getStart()) + "ms");
            log.debug("----------------------\tDSF-SPRINGBOOT " + common.getUuid() + " end\t\t--------------------\n");
            startThreadLocal.remove();
        }
    }

    private class Common {
        private long start;
        private String uuid;

        public Common(long start, String uuid) {
            this.start = start;
            this.uuid = uuid;
        }

        public long getStart() {
            return start;
        }

        public String getUuid() {
            return uuid;
        }
    }
}
