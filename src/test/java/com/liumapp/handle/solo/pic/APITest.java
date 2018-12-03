package com.liumapp.handle.solo.pic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.handle.solo.pic.bean.QiNiu;
import com.liumapp.qtools.http.HttpTool;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * file APITest.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/12/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HandleSoloPicMain.class})
public class APITest {

    @Autowired
    private QiNiu qiNiu;

    @Test
    public void getItemList () throws Exception {
        Configuration cfg = new Configuration();
        cfg.useHttpsDomains = false;
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiNiu.getAppKey(), qiNiu.getSecretKey());
        String token = auth.uploadToken(qiNiu.getBucket());
        Response r = uploadManager.put("hello world".getBytes(), "yourkey", token);
        System.out.println(r);

    }

}
