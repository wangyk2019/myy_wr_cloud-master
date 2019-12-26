package com.moyuan.myyzuul;

import com.moyuan.myyzuul.filter.LoginFilter;
import com.moyuan.myyzuul.filter.PostFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableSwagger2
@EnableFeignClients
public class MyyZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyyZuulApplication.class, args);
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
}
