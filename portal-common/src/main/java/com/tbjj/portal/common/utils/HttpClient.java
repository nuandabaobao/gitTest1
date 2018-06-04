package com.tbjj.portal.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9/009.
 */
public class HttpClient {

    private static CloseableHttpClient httpClient=HttpClients.createDefault();

    /**
     * get请求带参数
     * @param url
     * @param map
     * @return
     */
    public static String doGet(String url,Map<String,Object> map){
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (map != null) {
               for (Map.Entry<String, Object> entry : map.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.setHeader("Accept","application/json");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get请求不带参数
     */
    public static String doGet(String url){
        return doGet(url,null);
    }

    /**
     * post请求带参数
     * @param url
     * @param map
     * @return
     */
    public static String doPost(String url,Map<String,Object> map){
        try{
            HttpPost httpPost=new HttpPost(url);
            httpPost.addHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Accept","application/json");
            if(map!=null){
                JSONObject json = JSONObject.fromObject(map);
                StringEntity entity=new StringEntity(json.toString(), Charset.forName("UTF-8"));
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity,"UTF-8");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * post请求不带参数
     * @param url
     * @return
     */
    public static String doPost(String url){
        return doPost(url, null);
    }

}
