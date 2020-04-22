package base;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableAsync
@Component
public class Main {

    public Integer max = Integer.MAX_VALUE;

    public static CountDownLatch count = new CountDownLatch(30);

    public static  AtomicInteger num = new AtomicInteger(0);

    private final static int oneDay = 23 * 60 * 60 *1000 + 59 * 60 * 1000 + 59 * 1000 + 999;

    public static final String PUSH_MESSAGE_TITLE = "收到来自%s的一条留言";
    //volatile

    public static AtomicInteger getNum() {
        return num;
    }

    @Resource(name = "addressDataExecutorPool")
    private Executor addressDataExecutorPool;

    private static int a = 0;
    private static boolean flag = false;

    public static final String HOUR_PATTERN_MS_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String HOUR_PATTERN_ = "yyyy-MM-dd HH:mm:ss";

    public static final String regex = "^([1-9]|10)$";

    public static final String da = "2020-10-11";



    public static void main(String[] args) throws InterruptedException, ParseException ,
            IOException, JsonParseException, JsonMappingException {
        try {
            Integer a = null;
            if(null == a || (8 / 0) == 1){

            }
            throw new RuntimeException();
        }catch (Exception e){
            int a = 5 / 0;
            throw new IndexOutOfBoundsException();
        }
    }



    private static void sortOrgCustomers(List<OrgCustomerDTO> orgCustomers, Map<Integer, Integer> orgLevelMap){
        orgCustomers.sort((OrgCustomerDTO orgDTO1, OrgCustomerDTO orgDTO2) ->{
            Integer level1 = orgLevelMap.get(orgDTO1.getOrgId());
            Integer level2 = orgLevelMap.get(orgDTO2.getOrgId());
            if(level1 != level2){
                return level1 - level2;
            }
            return orgDTO1.getFirstSourceTime().compareTo(orgDTO2.getFirstSourceTime());
        });
    }

    public static void change(String a){
        a = "ss2";
    }

    static class ThreadA extends Thread{
        public void run(){
            a = 1;
            flag = true;
        }
    }

    static class ThreadB extends Thread{
        public void run(){
            if(flag){
                a = a * 1;
            }
            if(a == 0) {
                System.out.println("**** ,a is zero");
            }
        }
    }

    public void print(List<Integer> a){
        for(int i = 0 ;i < a.size() ; i++) {
            System.out.print(a.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * 分割参数（Integer） 将list参数分割为批量请求参数
     *
     * @param param 参数
     * @return {@link List<Integer>}
     */
    public <T> List<List<T>> splitParam(List<T> param, Integer size) {
        int limit = (param.size() + size - 1) / size;
        List<List<T>> mglist = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(param.stream().skip(i * size).limit(size).collect(Collectors.toList()));
        });
        return mglist;
    }

    public List<Integer> repeat(List<Integer> a){
        List<Integer> result = new ArrayList<>();
        for(Integer index : a){
            Integer i = a.get(index);
            result.add(i);
        }
        return result;
    }

    public static String encrypt(String data, String deskey) throws Exception {  //对string进行BASE64Encoder转换
        //先进行des加密
        byte[] bt = encryptByKey(data.getBytes("utf-8"), deskey);
        //base64编码
        BASE64Encoder base64en = new BASE64Encoder();
        String strs = new String(base64en.encode(bt));
        return strs;
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @return byte[]
     */
    private static byte[] encryptByKey(byte[] datasource, String key) {
        try {
            SecureRandom random = new SecureRandom();

            DESKeySpec desKey = new DESKeySpec(key.getBytes("utf-8"));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            //用密匙初始化Cipher对象
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);

            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource, 0, datasource.length);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    public void test2(){
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            // to do something
        }
        System.out.println("测试异步任务+++");
    }

    public void test1() throws InterruptedException{
        Thread.sleep(3000);
    }

    /**
     * 解析地址
     *
     * @param address
     * @return
     */
    public List<Map<String, String>> addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|北京市|天津市|上海市|重庆市)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        List<Map<String, String>> table = new ArrayList<Map<String, String>>();
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            table.add(row);
        }
        return table;
    }
}

class ThreadTest {

    int a = 0;

    public void test1(){
        for(int i = 0 ; i < 1000; i++) {
            System.out.println("a=" + (a++));
        }
    }

    public void test2(){
        for(int i = 0 ; i < 1000; i++) {
            System.out.println("aa=" + (a++));
        }
    }

}


class BeanDefinaitionMySelf {

    BigDecimal test;

    public BigDecimal getTest() {
        return test;
    }

    public void setTest(BigDecimal test) {
        this.test = test;
    }
}