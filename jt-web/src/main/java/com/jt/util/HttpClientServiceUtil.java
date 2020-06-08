package com.jt.util;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class HttpClientServiceUtil {
    // 连接池对象
    @Resource
    private CloseableHttpClient httpClient;
    @Resource
    private RequestConfig requestConfig;

    /**
     * Get请求
     */
    public String doGet(String url, Map<String, String> paramMap, String charset) {
        //设置默认字符集
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        //拼接URL 和参数
        if (paramMap != null && paramMap.size() > 0) {
            //拼接utl头
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            //遍历map
            for (Map.Entry<String, String> en : paramMap.entrySet()) {
                sb.append(en.getKey()).append("=").append(en.getValue()).append("&");
            }
            sb.delete(sb.length() - 1, sb.length());
            url = sb.toString();
        }
        System.out.println("当前URL是：" + url);
        //设置请求url
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        return getParam(charset, httpGet);
    }

    public String doGet(String url, Map<String, String> paramMap) {
        return this.doGet(url, paramMap, null);
    }

    public String doGet(String url) {
        return this.doGet(url, null, null);
    }
    /**
     * Post请求
     */
    public String doPost(String url,Map<String, String> paramMap, String charset)  {

        //设置默认字符集
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        //设置请求url
        HttpPost httpPost = new HttpPost(url);
        //设置post规则
        httpPost.setConfig(requestConfig);
        //设置发送参数
        if (paramMap != null && !paramMap.isEmpty()){
            //遍历map 存入List<NameValuePair>
            List<NameValuePair> paramList =new ArrayList();
            for (Map.Entry<String, String> en : paramMap.entrySet()) {
                paramList.add(new BasicNameValuePair(en.getKey(),en.getValue()));
            }
            UrlEncodedFormEntity  entity= null;
            try {
                entity = new UrlEncodedFormEntity(paramList, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
        }
        return getParam(charset, httpPost);
    }
    public String doPost(String url,Map<String, String> paramMap)  {
        return this.doPost(url,paramMap,null);
    }
    public String doPost(String url)  {
        return this.doPost(url,null,null);
    }


    /*=======================================================================================*/
    //方法一  发送请求 获取String响应
    private String getParam(String charset, HttpUriRequest httpUriRequest) {
        try {
            HttpResponse execute = httpClient.execute(httpUriRequest);
            //获取状态码
            int status = execute.getStatusLine().getStatusCode();
            System.out.println(status);
            if (status == 200) {
                //获取实体
                HttpEntity entity = execute.getEntity();
                String param = EntityUtils.toString(entity, charset);
                return param;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("url链接失败");
        }
    }

}
