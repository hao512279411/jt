package Test1;

import com.jt.util.HttpClientServiceUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class TestHttpClient {

    @Test
    public void estHttpClient() throws IOException {
        //1、指定url地址
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        //2、创建Client对象
        HttpClient httpClient = HttpClients.createDefault();
        //3、发送请求
        HttpResponse response = httpClient.execute(httpGet);
        //4、查看响应状态码
        int status =response.getStatusLine().getStatusCode();
        if (status == 200) { //响应正常
            //5、获取响应实体 HTML页面
            HttpEntity entity = response.getEntity();
            //6、用工具类 转换HTML网页为 String
            String htmlString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(htmlString);
        }


    }



    @Test
    public void test01(){
        HttpClientServiceUtil httpClientServiceUtil =new HttpClientServiceUtil();

//        Map<String , String> paramMap = new HashMap();
        String url  = "https://www.baidu.com";
//
//        paramMap.put("a","a");
//        paramMap.put("b","b");
//        paramMap.put("c","c");


        String s = httpClientServiceUtil.doGet(url);
        System.out.println(s);


    }

}
