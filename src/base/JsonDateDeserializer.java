package base;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * 自定义Jackson反序列化日期类型时应用的类型转换器
 *
 * @author 郝文超
 * @date 2019/11/17
 * @since 1.0.0
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String source = p.getText();
        Date targetDate = null;
        if (StringUtils.isNotEmpty(source)) {
            try {
                source = source.trim();
                targetDate = DateUtils.parseDate(source, CommonConstant.DATETIME);
            } catch (ParseException ex) {
                return (Date) ctxt.handleWeirdStringValue(handledType(), source,
                        "expected format \"%s\"");
            }
        }
        return targetDate;
    }


    @Override
    public Class<?> handledType() {
        return Date.class;
    }
}
