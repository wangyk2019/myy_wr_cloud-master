package com.moyuan.myyzuul.filter;

import com.moyuan.myyzuul.pojo.ServersError;
import com.moyuan.myyzuul.service.ServerserrorService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class PostFilter extends ZuulFilter{
	
	private Logger logger = LoggerFactory.getLogger(PostFilter.class);
	
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
        return "post";
    }

    /**
     * 过滤器顺序，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 5;
    }


    /**
     * 过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            request.setCharacterEncoding("utf-8");
            InputStream in = request.getInputStream();
            String reqBbody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            System.out.println(request.getRequestURI());
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    @Autowired
    ServerserrorService serverserrorService;
    /**
     * 业务逻辑
     * @return
     */
    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            request.setCharacterEncoding("utf-8");
            InputStream in = request.getInputStream();
            String reqBbody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            // 打印userId，获取其他用户信息
//            if (reqBbody != null) {
//                JSONObject json = JSONObject.fromObject(reqBbody);
//                Object userId = json.get("userId");
//                if (userId != null) {
//                    logger.info("request userId:\t" + userId);
//                }
//            }
            // 打印请求方法，路径
            logger.info("request url:\t" + request.getMethod() + "\t" + request.getRequestURL().toString());
            Map<String, String[]> map = request.getParameterMap();
            // 打印请求url参数
            StringBuilder sb = new StringBuilder();
            if (map != null) {
                sb.append("request parameters:\t");
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    sb.append("[" + entry.getKey() + "=" + printArray(entry.getValue()) + "]");
                }
                logger.info(sb.toString());
            }
//            // 打印请求json参数
//            if (reqBbody != null) {
//                logger.info("request body:\t" + reqBbody);
//            }

            // 打印response
            InputStream out = ctx.getResponseDataStream();
            String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
            if (outBody != null) {
                logger.info("response body:\t" + outBody);
            }

            ctx.setResponseBody(outBody);//重要！！！

            ServersError serversError = new ServersError();
            serversError.setMethod(request.getMethod()+" "+request.getRequestURL().toString());
            serversError.setParam(sb.toString());
            serversError.setRequestbody(reqBbody);
            serversError.setResponsebody(outBody);
            serverserrorService.addObj(serversError);

//            ctx.addZuulResponseHeader("testToken", "wyk");

//            HttpServletResponse response = ctx.getResponse();
//            String token = response.getHeader("token");

//            InputStream stream = RequestContext.getCurrentContext().getResponseDataStream();
//            try {
//                String body = IOUtils.toString(stream);
//                System.err.println(body);
//                RequestContext.getCurrentContext().setResponseBody(body);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;  

    }

    String printArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


}
