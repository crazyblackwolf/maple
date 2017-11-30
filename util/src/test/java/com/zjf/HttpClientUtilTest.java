package com.zjf;

import com.alibaba.fastjson.JSONObject;
import com.zjf.utils.http.HttpClientUtil;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/21.
 */
public class HttpClientUtilTest {

    @Test
    public void testGet() throws URISyntaxException {
        String url = "http://restapi.amap.com/v3/weather/weatherInfo";
        String city = "";
        Map<String, Object> params = new HashMap<>(4);
        params.put("city", "成都");
        params.put("key", "");
        params.put("output", "JSON");
        String result = HttpClientUtil.httpGetRequest(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
    }
}
