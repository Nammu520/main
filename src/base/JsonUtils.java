package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Json工具类
 *
 * @author: 郝文超
 * @date: 2019/10/17
 * @since: v1.0.0
 */
public final class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return mapper;
    }

    static {
        // 序列化：NULL值不序列化
//        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        // 序列化：时间类型序列化格式设定为ISO-8601格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
        // 序列化：时区设定为东八区
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 反序列化：允许对象忽略json中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 反序列化：
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        // 反序列化：允许将枚举未知值反序列化为NULL
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        // 反序列化：使用自定义的日期类型转换器
        SimpleModule deserializeModule = new SimpleModule("DeserializeModule", new Version(1, 0, 0, null));
        deserializeModule.addDeserializer(Date.class, new JsonDateDeserializer()); // assuming serializer declares correct class to bind to
        mapper.registerModule(deserializeModule);
    }

    private JsonUtils() {
    }

    /**
     * 将Object对象序列化为Json字符串
     *
     * @param obj Object对象
     * @return Json字符串
     * @throws JsonProcessingException 异常
     */
    public static String serializeObjectToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        }
        return null;
    }

    /**
     * 将Json字符串反序列化为泛型类对象
     *
     * @param json  Json字符串
     * @param clazz 类
     * @param <T>   泛型
     * @return 反序列化后的泛型类对象
     * @throws Exception 异常
     */
    public static <T> T deserializeJsonToObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将Json字符串反序列化为泛型类对象
     *
     * @param json    Json字符串
     * @param typeRef TypeReference
     * @param <T>     泛型
     * @return 反序列化后的泛型类对象
     * @throws Exception 异常
     */
    public static <T> T deserializeJsonToObject(String json, TypeReference<T> typeRef) {
        try {
            return mapper.readValue(json, typeRef);
        } catch (Exception e) {
        }
        return null;
    }
}
