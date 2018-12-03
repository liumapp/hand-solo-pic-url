# hand-solo-pic-url
2018年9月，七牛云回收了所有测试域名，这直接导致各种博客系统的所有图片资源全部失效，包括了我自己的solo博客，这个项目记录了这个问题的解决办法

## 解决步骤

* 登录七牛云后台，配置自己的域名

    * 详细步骤：七牛云后台 -> 域名管理 -> 添加域名 -> 添加自己的域名记录（比如我的 oss.liumapp.com）
    
    * 注意事项：七牛云会要求您的域名必须完成备案，否则无法添加，并且添加成功后，七牛云会提供一串CNAME地址，您需要把这串CNAME地址跟自己的域名解析起来。
    
* 在七牛云后台配置对象存储

    * 详细步骤：七牛云后台 -> 对象存储 -> 选择自己的存储空间 -> 内容管理 -> 设置外链默认域名为刚刚配置成功的域名（oss.liumapp.com）
    
    * 注意事项：添加域名后，需要等待七牛云的审核，审核通过后才可以进行这一步的操作，操作完成后，可以在内容管理列表中，直接选择一张图片来看看外链地址：(http://oss.liumapp.com/004a8d56bba1416a8e004447a65bcf08.jpg)     
    
* 接下来修改solo数据库，把所有博文的图片资源地址中的测试域名，替换为自己刚刚设置好的域名（oss.liumapp.com）即可，但是我这里还想做一些特殊操作

    * 替换sql：
    
            SET SQL_SAFE_UPDATES = 0;
    
            UPDATE b3_solo_article SET articleContent = REPLACE( articleContent,'http://你之前七牛测试域名地址','http://oss.liumapp.com') ;
            
        我之前的七牛测试域名是：http://om40sen9v.bkt.clouddn.com，那么sql就是：
        
            UPDATE b3_solo_article SET articleContent = REPLACE( articleContent,'http://om40sen9v.bkt.clouddn.com','http://oss.liumapp.com') ;
            
        在sql命令行或者相关工具中对文章表（大D在新版本的solo中似乎改动了数据表，所以您在操作之前，请确认一遍表名与字段名是否匹配）进行修改。
        
        各位看官读到这里，基本上就解决了自己博客图片资源路径失效的问题，接下来记录的内容，介绍了如何将七牛云的图片资源转移到GitHub上（换句话说，就是利用GitHub作为自己站点的OSS存储服务，我之所以想要这么折腾一番，是希望在后续将博客的前后端进行分离实现，前端整体部署到GitHub，后端则利用自己的ECS资源仅仅提供API服务）。            

    * 因为我个人的七牛云空间就快满了（免费空间只有600M），而我仅仅是拿来存储博客的图片而已，所以我打算使用GitHub来进行图片的保存。

* 使用GitHub存储所有博客图片:

    * 首先可以使用官方推荐的python脚本(script/download-process.py)将七牛云oss.liumapp.com空间上的所有资源下载下来
    
    * 对python不熟悉的同学，手撸Java代码，再通过七牛云的api也是可以达到批量下载的目的（）
    
    
    
    
    
            