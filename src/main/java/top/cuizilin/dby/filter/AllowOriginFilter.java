package top.cuizilin.dby.filter;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "allowOriginFilter",urlPatterns = "/**")
@Component
public class AllowOriginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException { }

    //拦截的方法
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");

        if (StringUtils.isNotBlank(origin)) {
            //设置响应头，允许跨域访问
            //带cookie请求时，必须为全匹配，不能使用*
            /**
             * 表示允许 origin 发起跨域请求。
             */
            response.addHeader("Access-Control-Allow-Origin", origin);
        }

        /**
         * GET,POST,OPTIONS，PUT,DELETE 表示允许跨域请求的方法
         */
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");

        /**
         * 表示在86400秒内不需要再发送预校验请求
         */
        response.addHeader("Access-Control-Max-Age", "86400");

        //支持所有自定义头
        String headers = request.getHeader("Access-Control-Request-Headers");
        if (StringUtils.isNotBlank(headers)) {
            //允许JSON请求，并进行预检命令缓存
            response.addHeader("Access-Control-Allow-Headers", headers);
        }

        response.addHeader("Access-Control-Max-Age", "3600");

        //允许cookie
        response.addHeader("Access-Control-Allow-Credentials", "true");
        try{
            filterChain.doFilter(request, response);
        }catch (Exception e){

        }

    }

    //销毁时候调用的方法
    public void destroy() { }
}