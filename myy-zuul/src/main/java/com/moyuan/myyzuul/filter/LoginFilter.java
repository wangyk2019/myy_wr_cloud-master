package com.moyuan.myyzuul.filter;

import com.moyuan.myyzuul.feign.SecurityClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class LoginFilter extends ZuulFilter{

    @Autowired
    SecurityClient securityClient;

	private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	/**
     * 过滤器类型，前置过滤器
     * filterType()过滤器类型
     * pre：可以在请求被路由之前调用
		routing：在路由请求时候被调用
		post：在routing和error过滤器之后被调用
		error：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器顺序，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    //排除过滤的 uri 地址
    private static final String LOGIN_URI = "/login/";
    /**
     * 过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String requestURI = request.getRequestURI();
        //登录和注册放行
        if(requestURI.contains(LOGIN_URI)){
            return false;
        }
        return true;
    }

    /**
     * 业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
    	logger.debug("*****************FirstFilter run start*****************");

        System.out.println("进入pre请求被路由之前的过滤器的处理方法e");
        logger.debug("*****************FirstFilter run end*****************");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
//        System.err.println("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
//        System.err.println("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
        StringBuilder params = new StringBuilder("?");

//        验证token时候 token的参数 从请求头获取
        String token = request.getHeader("token");
        System.out.println(token);
//        if (!securityClient.getToken(token)) {
//            //返回错误提示
//            currentContext.setSendZuulResponse(false);  //false  不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
//            currentContext.setResponseBody("token 错误");
//            currentContext.setResponseStatusCode(403);
//            return null;
//        }

//        boolean secuR = securityClient.auth(token,request.getRequestURI());
//        if (!secuR) {
//            //返回错误提示
//            currentContext.setSendZuulResponse(false);  //false  不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
//            currentContext.setResponseBody("没权限");
//            currentContext.setResponseStatusCode(401);
//            return null;
//        }

        Enumeration<String> names = request.getParameterNames();
        if( request.getMethod().equals("GET") ) {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }

        if (params.length() > 0) {
            params.delete(params.length()-1, params.length());
        }
        System.err.println("REQUEST:: > " + request.getMethod() + " " + request.getRequestURI() + params + " " + request.getProtocol());

        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            String value = request.getHeader(name);
            System.err.println("REQUEST:: > " + name + ":" + value);
        }

        final RequestContext ctx = RequestContext.getCurrentContext();
        if (!ctx.isChunkedRequestBody()) {
            ServletInputStream inp = null;
            try {
                inp = ctx.getRequest().getInputStream();
                String body = null;
                if (inp != null) {
                    body = inp.toString();
//                    body = IOUtils.toString(inp);
                    System.err.println("REQUEST:: > " + body);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


}
