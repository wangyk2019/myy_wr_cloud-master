package com.moyuaninfo.liveplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
public class LiveplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveplayApplication.class, args);
    }


}
