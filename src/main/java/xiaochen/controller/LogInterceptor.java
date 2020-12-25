package xiaochen.controller;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(DateFormatUtils.format(new Date(), "hh:MM:ss  -> ") + request.getRequestURI() + ",请求了\n");
        return super.preHandle(request, response, handler);
    }
}
