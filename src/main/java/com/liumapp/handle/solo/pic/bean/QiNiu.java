package com.liumapp.handle.solo.pic.bean;

import org.springframework.stereotype.Component;

/**
 * file QiNiu.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/12/3
 */
@Component
public class QiNiu {

    private String appKey;

    private String secretKey;

    private String bucket;

    public QiNiu() {
    }

    public QiNiu(String appKey, String secretKey, String bucket) {
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
    }

    public String getAppKey() {
        return appKey;
    }

    public QiNiu setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public QiNiu setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public QiNiu setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }
}
