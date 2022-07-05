package com.example.demoi18n.i18n_utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.File;

/**
 * Spring配置
 * @author zz
 * @date 2020/2/28 15:50
 **/
@Slf4j
@Configuration
public class SpringConfig {

    @Primary
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
//        String path = System.getProperty("user.dir") + File.separator + "src/main/resources/i18n/messages";
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheMillis(10);
        return messageSource;
    }
}