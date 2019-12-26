package com.moyuan.myyeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyyEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyyEurekaApplication.class, args);
    }

}
