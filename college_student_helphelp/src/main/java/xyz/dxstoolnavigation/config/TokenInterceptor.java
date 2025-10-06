package xyz.dxstoolnavigation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.dxstoolnavigation.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    
    @Autowired
    private TokenUtils tokenUtils;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行登录接口
        if (request.getRequestURI().contains("/api/login")) {
            return true;
        }
        
        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 移除 "Bearer " 前缀
        }
        
        // 验证token
        if (tokenUtils.validateToken(token)) {
            return true;
        }
        
        // token无效或过期，返回401状态码
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":401,\"msg\":\"未授权或登录已过期\"}");
        return false;
    }
}