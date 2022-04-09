package com.ac.interceptor;

import com.ac.utils.TokenState;
import com.ac.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName TokenCheckInterceptor
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/23 12:30
 * @Version 1.0
 **/
@Slf4j
public class TokenCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("开始检验token");
        //从请求头中获取token
        String token = request.getHeader("token");
        log.info(token);
        Map<String, Object> resultMap = TokenUtil.validToken(token);
        TokenState state = TokenState.getTokenState((String) resultMap.get("state"));
        switch (state) {
            case VALID:
                return true;
            case EXPIRED:
            case INVALID:
                log.info("无效token");
                //token过期或者无效，则输出错误信息返回给ajax
                request.setAttribute("success", false);
                return false;
        }
        return false;
    }
}
