package com.moyuaninfo.cloud;

import com.moyuaninfo.cloud.netty.MyNettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

//@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableSwagger2
@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
public class CloudNettyApplication {

	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(CloudNettyApplication.class);
		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);

		try {
			new MyNettyServer(configurableApplicationContext).start(10086);
		}catch(Exception e) {
			System.out.println("NettyServerError:"+e.getMessage());
		}
	}

	@Bean
	public StartUpTask startupTask() {
		return new StartUpTask();
	}


}
