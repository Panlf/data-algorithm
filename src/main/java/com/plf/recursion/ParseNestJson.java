package com.plf.recursion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 解析复杂的JSON字符串
 * 使用递归算法解析
 * @author  panlf
 * @date  2020-06-05
 */
@Slf4j
public class ParseNestJson {
    /**
     * 背景
     *      我们拿到一个JSON字符串,不清楚是JSONObject or JSONArray
     *      且里面嵌套多层JSON字符串,我们要对里面的对应key的value进行数据处理,并返回
     */

    @Test
    public void test(){
        String context = "{\"id\":1,\"age\":12,\"content\":[{\"name\":\"qs\",\"code\":7},{\"name\":\"ew\",\"code\":13}],\"friend\":{\"name\":\"re\",\"address\":[{\"name\":\"m\"},{\"name\":\"g\"}]}}";
        List<String> list = Arrays.asList("name");
        Object result = parseJson(context,list);

        log.info(JSON.toJSONString(result));
    }

    /**
     * 递归解析嵌套的JSON字符串
     * @param oValue
     * @param list
     * @return
     */
    public Object parseJson(String oValue, List<String> list) {
        if(isJson(oValue)) {
            JSONObject jsonObject = JSONObject.parseObject(oValue);
            for(String key : jsonObject.keySet()) {
                String value = jsonObject.getString(key);
                if(!isJson(value) && !isJsonArray(value)) {
                    if(list.contains(key)) {
                        jsonObject.put(key, dealStr(value));
                    }
                }else {
                    jsonObject.put(key, parseJson(value,list));
                }
            }
            return jsonObject;
        }
        if(isJsonArray(oValue)) {
            JSONArray jsonArray = JSONArray.parseArray(oValue);
            for(int i=0;i<jsonArray.size();i++) {
                jsonArray.set(i,parseJson(jsonArray.get(i).toString(),list));
            }
            return jsonArray;
        }
        return null;
    }

    /**
     * 判断字符串是否为JSONObject
     *
     * @param content
     * @return
     */
    public boolean isJson(String content) {
        if (!notEmpty(content)) {
            return false;
        }
        try {
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符串是否可以转化为JSON数组
     *
     * @param content
     * @return
     */
    public boolean isJsonArray(String content) {
        if (!notEmpty(content))
            return false;
        try {
            JSONArray.parseArray(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 自定义的处理前的数据
     * @param originData
     * @return
     */
    public String dealStr(String originData) {
        if(notEmpty(originData)){
            return originData.toUpperCase();
        }
        return originData;
    }

    /**
     * 判断数据为Not Null and Length GT 0
     * @param data
     * @return
     */
   public boolean notEmpty(String data){
        if(data!=null && data.trim().length()>0){
            return true;
        }
        return false;
   }
}
