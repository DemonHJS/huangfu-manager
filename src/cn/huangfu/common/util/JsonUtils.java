package cn.huangfu.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtils {
	/**
	 * 忽略html字符的转, 为空数据返回null
	 */
	private final static Gson GSON = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().disableHtmlEscaping().create();

	/** 转换为JSON格式 */
	public static String toJson(Object data) {
		return GSON.toJson(data);
	}
	
	/**
	 * Json转制定类型对下根据类型
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Type type){
		return GSON.fromJson(json, type);
	}
	
	public static <T> T formJson(String json,Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}
	
	public static <T> T formJson(Reader json,Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}
	
	/**
	 * 数据类型过滤：yyyy-MM-dd HH:mm:ss转yyyy-MM-dd
	 * @param source
	 * @param field
	 * @return
	 */
	public static String parseDate(String source,String field){
		return source.replaceAll("(\""+field+"\":\"\\d{4}-\\d{2}-\\d{2})\\s+\\d{2}?:\\d{2}?:\\d{2}?","$1" );		
	}

	public static void main(String[] args) {
		String fromDataJson = "{\"tabtext\":\"2222\",\"tabdate\":\"1992-04-09 17:45:36\",\"tabdates\":\"1992-04-09 17:45:36\",\"tabint\":555,\"tabfloat\":555}";
		Map<String,Object> fromMap = JsonUtils.fromJson(fromDataJson,new TypeToken<Map<String,Object>>() {}.getType());
		System.out.println(fromMap);
	}
}
