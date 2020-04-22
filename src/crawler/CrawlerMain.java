package crawler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CrawlerMain {

    // 爬取京东评论数据
    public static void main(String[] args) throws IOException{
        System.getProperties().setProperty("proxySet", "true");

        Map<String, String> map = new HashMap<>();
        //map.put("凌度V380", "100004451789");
        map.put("360-G300", "5535583");
        map.put("360-G600","100001363976");
       // map.put("小米-1S", "100000634159");
       // map.put("70迈-PRO", "8621900");
        //map.put("70迈-1S", "5757715");
        List<Thread> threadList = new ArrayList<>();
        map.keySet().stream().forEach(key -> {
            SonThread runner = new SonThread();
            runner.setProductName(key);
            runner.setProductId(map.get(key));
            runner.run();
//            Thread thread = new Thread(runner);
//            threadList.add(thread);
        });
//        for(Thread thread : threadList) {
//            thread.start();
//        }
    }


}
