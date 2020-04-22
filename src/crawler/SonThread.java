package crawler;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SonThread implements Runnable{

    private String productId;

    private String productName;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public void run() {
        try {
            // 获取最大页
            String response = this.doGet(productId, 0);
            Map<String, Object> map = JSON.parseObject(response);
            Integer maxPage = (Integer)map.get("maxPage");
            List<String> contents = new ArrayList<>();
//            if(maxPage > 5) {
//                maxPage = 5;
//            }
            String txtName = "e:\\差评数据\\" + productName + ".txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(txtName, true));
            for(int i = 0; i <= maxPage; i++) {
                String resp = this.doGet(productId, i);
                Map<String, Object> map2 = JSON.parseObject(resp);
                List<Map<String, Object>> comments = (List<Map<String, Object>>)map2.get("comments");
                for(Map<String, Object> temp : comments) {
                    String content = (String)temp.get("content");
                    content = content.replaceAll("\r|\n", "");
                    out.write(content + "\r\n");
                }
                // 避免频繁调用,ip被封
                Thread.sleep(5000);
            }
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(productName + "差评数据爬取完成");
    }

    public String getUrl(String productId, Integer page) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://sclub.jd.com/comment/productPageComments.action?");
        sb.append("productId=").append(productId).append("&page=").append(String.valueOf(page));
        sb.append("&score=1&pageSize=10&isShadowSku=0&fold=1&sortType=5");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String doGet(String productId, Integer page) {
        String resp = "";
        try {
            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
            String url = this.getUrl(productId, page);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 创建Get请求
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("User-A", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            // 由客户端执行(发送)Get请求
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }
}
