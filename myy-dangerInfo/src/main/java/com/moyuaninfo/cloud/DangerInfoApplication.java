package com.moyuaninfo.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients
public class DangerInfoApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(DangerInfoApplication.class).run(args);
    }
}

