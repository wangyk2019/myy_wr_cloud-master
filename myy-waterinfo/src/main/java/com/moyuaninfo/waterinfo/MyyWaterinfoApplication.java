package com.moyuaninfo.waterinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableSwagger2
public class MyyWaterinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyyWaterinfoApplication.class, args);
    }

}
