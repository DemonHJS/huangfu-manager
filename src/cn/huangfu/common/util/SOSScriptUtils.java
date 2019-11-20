package cn.huangfu.common.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class SOSScriptUtils {

	static ScriptEngineManager sem=new ScriptEngineManager();
	static ScriptEngine se= sem.getEngineByName("javascript");

	/**
	 * @author zhufang
	 *
	 * @param js js文本内容
	 * @param funName js中的方法
	 * @param values
	 * @return
	 *
	 *
	 */
	public static Map<String, Object> parse(String js,String funName,Object... values){
		try {
			se.eval(js);
			Invocable invocable=(Invocable) se;
			Object x=invocable.invokeFunction(funName,values);
			if(x==null){
				System.out.println("no data return");
				return null;
			}
			System.out.println(x.toString());
			return JsonUtils.formJson(new String(x.toString().getBytes("GBK"),"utf-8"), Map.class);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("data format error");
		}
		return null;
	}
	/**
	 * @author zhufang
	 *
	 * @param in 文件
	 * @param funName js中的方法
	 * @param values
	 * @return
	 *
	 *
	 */
	public static String parseForObject(InputStream in,String funName,Object... values){
		try {
			se.eval(new InputStreamReader(in,"utf-8"));
			Invocable invocable=(Invocable) se;
			Object x=invocable.invokeFunction(funName,values);
			if(x==null){
				System.out.println("no data return");
				return null;
			}
			return x.toString();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("data format error");
		}
		return null;
	}
	/**
	 * @author zhufang
	 *
	 * @param js js文本内容
	 * @param funName js中的方法
	 * @param values
	 * @return
	 *
	 *
	 */
	public static String parseForObject(String js,String funName,Object... values){
		try {
			se.eval(js);
			Invocable invocable=(Invocable) se;
			Object x=invocable.invokeFunction(funName,values);
			if(x==null){
				System.out.println("no data return");
				return null;
			}
			return new String(x.toString().getBytes("GBK"),"utf-8");
//			System.out.println(x.toString());
//			return JsonUtils.fromJson(new String(x.toString().getBytes("GBK"),"utf-8"));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("data format error");
		}
		return null;
	}
	/**
	 * @author zhufang
	 *
	 * @param in 文件
	 * @param funName js中的方法
	 * @param values
	 * @return
	 *
	 *
	 */
	public static Map<String, Object> parse(InputStream in,String funName,Object... values){
		try {
			se.eval(new InputStreamReader(in,"utf-8"));
			Invocable invocable=(Invocable) se;
			Object x=invocable.invokeFunction(funName,values);
			if(x==null){
				System.out.println("no data return");
				return null;
			}

			return JsonUtils.formJson(x.toString(), Map.class);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("data format error");
		}
		return null;
	}
	
	
	public static void main(String[] args) {
//		{"":"\""+ssd+"\""}
//		String js="function x(){"
//				+ "var date=new Date();"
//				+ "var data={"
//				+ "\"year\":\"\"+date.getFullYear()+\"\","
//				+ "\"month\":\"\"+date.getMonth()+\"\","
//				+ "\"day\":\"\"+date.getDate()+\"\""
//				+ "};"
//				+ "return JSON.stringify(data);"
//				+ "}";
//		Map<String,Object> p=parse(js,"x");
//		System.out.println(p.get("year"));
//		
//		Map<String, Object> dataMap=null;
//		try {
//			File filexx=new File("C:/Users/13967745053/Desktop/tt/1.json");
//			BufferedReader br=new BufferedReader(new FileReader(filexx));
//			
//			dataMap = (Map<String, Object>) ((Map<String, Object>) JsonUtils.fromJson(br.readLine()).get("data")).get("datas");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String js="function shebao(d){"
//				+ "var shebao={\"xb\":{\"0\":\"女\",\"1\":\"男\"},"
//				+ "\"yanglao\":{\"-1\":\"\",\"1\":\"参保缴费\"},"
//				+ "\"yiliao\":{\"-1\":\"\",\"1\":\"参保缴费\"},"
//				+ "\"gongshi\":{\"-1\":\"\",\"1\":\"参保缴费\"},"
//				+ "\"shengyu\":{\"-1\":\"\",\"1\":\"参保缴费\"},"
//				+ "\"shiye\":{\"-1\":\"\",\"1\":\"参保缴费\"}"
//				+ "};"
//				+ "var nowshebao={};"
//				+ "var dd=JSON.parse(d);"
//				+ "for(var x in shebao){"
//				+ "		nowshebao[x]=shebao[x][dd[x]];"
//				+ "}"
//				+ "var date=new Date();"
//				+ "nowshebao['year']=\"\"+date.getFullYear()+\"\";"
//				+ "nowshebao['month']=\"\"+date.getMonth()+\"\";"
//				+ "nowshebao['day']=\"\"+date.getDate()+\"\";"
//				+ "var len=dd['jfxx'].length;"
//				+ "nowshebao['len']=len;"
//				+ "nowshebao['beginYear']=dd['jfxx'][0].nian;"
//				+ "nowshebao['beginMonth']=dd['jfxx'][0].yue;"
//				+ "nowshebao['endYear']=dd['jfxx'][len-1].nian;"
//				+ "nowshebao['endMonth']=dd['jfxx'][len-1].yue;"
//				+ "return JSON.stringify(nowshebao);"
//				+ "}";
//		System.out.println( JsonUtils.toJson(dataMap));
//		Object obj=ScriptUtils.parse(js, "shebao", JsonUtils.toJson(dataMap));
//		System.out.println(JsonUtils.toJson(obj));
	}
}
