package me.jy.web.interceptors;

import me.jy.util.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jy
 */
@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RateLimiter rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // request.getUserPrincipal().getName() + ":" + request.getRequestURI();
        if (rateLimiter.limit("user:" + request.getRequestURI())) {
            throw new RuntimeException("Request too many times");
        }
        return true;
    }

}
