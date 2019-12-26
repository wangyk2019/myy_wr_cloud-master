package com.moyuaninfo.waterinfo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * @Description
 * @Author zhaoGq
 * @Date 2019/12/2 9:14
 * @Param 
 * @param null
 * @Return 
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.moyuaninfo.waterinfo.dao"})
public class MybatisConfig {

    //开启驼峰命名方式
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        ConfigurationCustomizer configurationCustomizer = new ConfigurationCustomizer(){
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //开启驼峰命名方式
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
        return configurationCustomizer;
    }

}
