package com.zjf.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author : jacky
 * @description :本工具类基于httpclient4.5.1实现 摘自http://blog.csdn.net/wangnan537/article/details/50374061
 * @date : 2017/11/18.
 */
public final class HttpClientUtil {

    private HttpClientUtil() {
        throw new AssertionError();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * get请求
     *
     * @param url 请求url
     * @return java.lang.String
     */
    public static String httpGetRequest(String url) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("----------请求URL={}", url);
        }
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**
     * get请求
     *
     * @param url    请求url
     * @param params 请求参数
     * @return java.lang.String
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("----------请求URL={},请求参数={}", url, params);
        }
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }

    /**
     * get请求
     *
     * @param url     请求url
     * @param headers 请求头
     * @param params  请求参数
     * @return java.lang.String
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("----------请求URL={},请求头={},请求参数={}", url, headers, params);
        }
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    /**
     * post请求
     *
     * @param url 请求url
     * @return java.lang.String
     */
    public static String httpPostRequest(String url) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("----------请求URL={}", url);
        }
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    /**
     * post请求
     *
     * @param url    请求url
     * @param params 请求参数
     * @return java.lang.String
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("----------请求URL={},请求参数={}", url, params);
        }
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    /**
     * post请求
     *
     * @param url     请求url
     * @param params  请求参数
     * @param headers 请求头
     * @return java.lang.String
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("----------请求URL={},请求头={},请求参数={}", url, headers, params);
        }
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("----------请求结果={}", result);
                }
                response.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return EMPTY_STR;
    }

}
