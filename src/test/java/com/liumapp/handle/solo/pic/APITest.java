package com.liumapp.handle.solo.pic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.handle.solo.pic.bean.QiNiu;
import com.liumapp.qtools.http.HttpTool;
import com.liumapp.qtools.http.UrlDownloadTool;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
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

import javax.naming.NoPermissionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
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

    private String dataPath = "/usr/local/tomcat/project/hand-solo-pic-url/data/";

    @Autowired
    private QiNiu qiNiu;

    @Test
    public void getItemList () throws Exception {
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiNiu.getAppKey(), qiNiu.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiNiu.getBucket(), "", 1000, "");
        LinkedList<FileInfo> fileList = new LinkedList<>();
        while (fileListIterator.hasNext()) {
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                fileList.add(item);
                System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);
            }
        }
        System.out.println("一共有" + fileList.size() + "个文件");
    }

    @Test
    public void downloadAllFile () throws IOException {
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiNiu.getAppKey(), qiNiu.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiNiu.getBucket(), "", 1000, "");
        while (fileListIterator.hasNext()) {
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println("ready to download : " + item.key + " to " + dataPath);
                String url = qiNiu.getDomain() + "/" + item.key;
                try {
                    UrlDownloadTool.NIODownload(url, dataPath + "/" + item.key);
                } catch (NoPermissionException e) {
                    System.out.println("download file failed , plz check your file folder permission");
                }
            }
        }
    }

}
