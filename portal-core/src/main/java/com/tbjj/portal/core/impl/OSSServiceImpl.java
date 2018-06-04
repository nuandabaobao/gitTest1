package com.tbjj.portal.core.impl;

import com.tbjj.portal.common.utils.JsonUtils;
import com.tbjj.portal.core.OSSService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by ebiz on 2017/10/20.
 */
@Service
public class OSSServiceImpl extends BaseServiceImpl implements OSSService {

    @Value("${tbjj.ossurl}")
    private String ossUrl;

    public String uploadFile(MultipartFile file){
        String url =null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName;
            //重命名文件
            fileName = UUID.randomUUID().toString().replaceAll("-", "")+fileSuffix;
            InputStream io = null;
            try {
                io = file.getInputStream();
                //参考文章http://blog.csdn.net/zknxx/article/details/72760315
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(ossUrl);
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.addBinaryBody("file",io, ContentType.create("multipart/form-data"),fileName);
                HttpEntity entity = builder.build();
                httpPost.setEntity(entity);
                HttpResponse response = client.execute(httpPost);
                String resultJson = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                return JsonUtils.getObjectStr(resultJson,"url");
            }catch (Exception e){
                logger.error("上传文件出现错误");
                return null;
            }
        }
        return url;
    }



    public String uploadFile(InputStream io, String fileName){
        //重命名文件
        fileName = UUID.randomUUID().toString().replaceAll("-", "")+fileName;
        try {
            //参考文章http://blog.csdn.net/zknxx/article/details/72760315
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(ossUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody("file",io, ContentType.create("multipart/form-data"),fileName);
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            String resultJson = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
            return JsonUtils.getObjectStr(resultJson,"url");
        }catch (Exception e){
            logger.error("上传文件出现错误");
            return null;
        }
    }
}
