package Interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Enumeration;
public class filter extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("===========AllInterceptor preHandle");
        Enumeration pNames   =   request.getParameterNames();
        while   (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            System.out.println(value);
            value=value.toLowerCase();
            if(value.indexOf("<script>")>-1|| value.indexOf("select")>-1||value.indexOf("update")>-1||
                    value.indexOf("delete")>-1||name.indexOf("insert")>-1){
                //System.out.println("12345");
                RequestDispatcher rd =request.getRequestDispatcher("../error.jsp");
                request.setAttribute("errorInfo", "The input has <'script'> or sql");
                rd.forward(request,response);
                return false;
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("===========AllInterceptor postHandle");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("===========AllInterceptor afterCompletion");
    }
}
