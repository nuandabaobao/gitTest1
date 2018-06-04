package com.tbjj.portal.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Created by ebiz on 2017/7/14.
 */
public class JsonUtils {
    public static String getObjectStr(String jsonString,String key){

        String[] keys = key.split("\\.");
        JSONObject object =(JSONObject) JSON.parse(jsonString);
        for (int i = 0; i < keys.length; i++) {

            String keyPart = keys[i];
            if (i!=keys.length-1) {
                //首先判断是否传入数组下标
                if(keyPart.indexOf("[")>=0){
                    //拿到下标
                    int index = Integer.parseInt(keyPart.substring(keyPart.indexOf("[")+1, keyPart.indexOf("]")));
                    JSONArray jsonArray = object.getJSONArray(keyPart.substring(0,keyPart.indexOf("[")));
                    object=jsonArray.getJSONObject(index);
                }else {
                    object = object.getJSONObject(keyPart);
                }
            }
        }
        //首先判断是否传入数组下标
        if(keys[keys.length-1].indexOf("[")>=0){
            //拿到下标
            int index = Integer.parseInt(keys[keys.length-1].substring(keys[keys.length-1].indexOf("[")+1, keys[keys.length-1].indexOf("]")));
            JSONArray jsonArray = object.getJSONArray(keys[keys.length-1].substring(0,keys[keys.length-1].indexOf("[")));
            return jsonArray.get(index).toString();
        }
        return object.getString(keys[keys.length-1]);
    }
}
