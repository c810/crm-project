package com.lihaopeng.crm.settings.web.interceptor;

import com.lihaopeng.crm.commons.constants.Constants;
import com.lihaopeng.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Connor
 * @date 2022/10/12 16:56
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 如果用户没有登录成功,则跳转到登录页面
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if(user==null){
            // httpServletResponse.sendRedirect("/crm"); // 重定向一定要加项目名,而这里也没有springmvc帮我们,所以必须加项目名
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()); // 动态获取
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
