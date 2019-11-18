package cn.huangfu.common.bean;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

	private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.CHINA);

	public static String get(String key) {
		return resourceBundle.getString(key);
	}

	public static String format(String key, Object... arguments) {
		return MessageFormat.format(get(key), arguments);
	}

	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	public static void main(String[] args) {
		//System.out.println(Json.toJson(resourceBundle.getKeys()));

	}

}
