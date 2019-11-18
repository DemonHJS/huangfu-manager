package cn.huangfu.common.util;

import org.json.JSONObject;
import org.json.XML;

public class XmlUtils {
	
//	public final static Log logger = LogFactory.getLog(XmlUtils.class);

    private XmlUtils(){}

	 /**
     * json to xml
     * @param json
     * @return
     */
    public static String jsonToXml(String json) {
        JSONObject jsonObj = new JSONObject(json);
        return XML.toString(jsonObj) ;
    }

    /**
     * xml to json
     * @param xml
     * @return
     */
    public static String xmlToJson(String xml) {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();
    }
}
