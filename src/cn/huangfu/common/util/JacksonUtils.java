package cn.huangfu.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/19 18:44
 * @Description:
 * Jackson工具类
 */
public class JacksonUtils {

    private static Log logger = LogFactory.getLog(JacksonUtils.class);

    private static ObjectMapper objectMapper=null;

    static{
        objectMapper = new ObjectMapper();
        // 去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        // 空值不序列化
//		objectMapper.setSerializationInclusion(Include.NON_NULL);
        // 反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 单引号处理
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JacksonUtils() {

    }

    /**
     * 返回ObjectMapper对象
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * json转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 对象转Json
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toCollection(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Json转Map
     * @param json
     * @return
     */
    public static Map<String,String> toMap(String json) {
        return toCollection(json,new TypeReference<Map<String,String>>(){});
    }

    /**
     * Json转List<String>
     * @param json
     * @return
     */
    public static List<String> toListString(String json) {
        return toCollection(json,new TypeReference<List<String>>(){});
    }


    /**
     * Json转List<Map<String,Object>>
     * @param json
     * @return
     */
    public static List<Map<String,Object>> toListMap(String json) {
        return toCollection(json,new TypeReference<List<Map<String,Object>>>(){});
    }


    public static TypeFactory getTypeFactory(){
        return objectMapper.getTypeFactory();
    }


}
