package com.liumapp.handle.solo.pic.config;

import com.liumapp.handle.solo.pic.bean.QiNiu;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * file QiNiuConfig.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/12/3
 */
@Configuration
public class QiNiuConfig {

    @Bean
    @ConfigurationProperties(prefix = "com.liumapp.qiniu")
    public QiNiu qiNiu () {
        QiNiu qiNiu = new QiNiu();
        return qiNiu;
    }


}
