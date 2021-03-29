package xiaochen.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xiaochen.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(DateUtil.curDate() + request.getRequestURI() + ",请求了\n");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(DateUtil.curDate() + request.getRequestURI() + ",结束了\n");
        super.afterCompletion(request, response, handler, ex);
    }
}
