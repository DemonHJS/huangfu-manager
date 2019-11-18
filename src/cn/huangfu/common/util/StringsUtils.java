package cn.huangfu.common.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringsUtils {

	private static Pattern pattern = Pattern.compile("(?<=[\\w])_[a-z]");


	public static void main(String[] args) {
		String str = ",";
		String rstr = "','";
		System.out.println(StringsUtils.replaceAll("22222222,3333",str ,rstr));

		Map<String,Object> rmap = new HashMap<String,Object>();
		rmap.put("ssss","中国人");

		System.out.println(replaceCont("shjshjdjsdjhsdhsdd[ssss],[ssss]",rmap,"[","]"));

		System.out.println(StringsUtils.upperUnderline("xxx_eee_dhasjd_ss"));
		System.out.println(StringsUtils.upperUpperCase("xxxEeeDhasjdSs"));
	}

	/**将下划线后面的字符大写
	 * a_b_c=>aBC
	 * @param str
	 * @return
	 */
	public static String upperUnderline(String str) {
		String newStr = str;
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			String g = matcher.group();
			String gg = g.toUpperCase().substring(1);
			newStr = newStr.replaceFirst(g, gg);
		}
		return newStr;
	}

	/**
	 * 将字符大写转下划线 aBC=>a_b_c
	 * @param str
	 * @return
	 */
	public static String upperUpperCase(String str) {
		StringBuilder sb = new StringBuilder();
		Stream.of(str.split("")).forEach(s -> {
			if(s.compareTo("A")>=0&&s.compareTo("Z")<=0){
				sb.append("_"+s.toLowerCase());
			}else{
				sb.append(s);
			}
		});
		return sb.toString();
	}

	/**
	 * 是否为空
	 * @param obj
	 * @return
	 */
	public static boolean empty(Object obj) {
		if (obj != null && !obj.toString().trim().equals("") && !obj.toString().equals("null")) {
			return false;
		}
		return true;
	}


	/**
	 * 是否为小数
	 * @param field
	 * @return
	 */
	public static boolean isFolat(String field)
	  {
	    int dotCount = 0;
	    int plusCount = 0;
	    int discountCount = 0;

	    for (int i = 0; i < field.length(); ++i) {
	      if ((field.charAt(i) < '0') || (field.charAt(i) > '9')) {
	        if (field.charAt(i) == '.') {
				++dotCount;
			}else if (field.charAt(i) == '-') {
				++discountCount;
			}else if (field.charAt(i) == '+') {
				++plusCount;
			}else {
	          return false;
	        }
	      }
	    }
	    if ((dotCount > 1) || (plusCount > 1) || (discountCount > 1)) {
	      return false;
	    }

	    if (field.indexOf("-") > 0) {
	      return false;
	    }
	    if (field.indexOf("+") > 0) {
	      return false;
	    }
	    Float decimal;
	    try
	    {
	      decimal = Float.valueOf(field);
	    } catch (Exception e) {
	      return false;
	    }
	    return true;
	  }

	/**
	 * 是否为数字
	 * @param field
	 * @return
	 */
	public static boolean isInt(String field)
	  {
	    try
	    {
	      Short.parseShort(field);
	    } catch (Exception e) {
	      return false;
	    }
	    return true;
	  }

	  public static boolean isDigital(String field)
	  {
	    int MAXLENGTH = 256;
	    if (field.getBytes().length > MAXLENGTH) {
	      return false;
	    }

	    for (int i = 0; i < field.length(); ++i) {
	      if ((field.charAt(i) < '0') || (field.charAt(i) > '9')) {
	        return false;
	      }
	    }
	    return true;
	  }

	  public static String getUnicode(String s) {
	    if ((s == null) || (s.length() == 0)) {
	      return "";
	    }
	    byte[] abyte0 = new byte[s.length()];
	    for (int i = 0; i < s.length(); ++i) {
	      abyte0[i] = (byte)s.charAt(i);
	    }

	    return new String(abyte0);
	  }


	/**
	 * 获取指定长度随机数字符串
	 * @param length 长度
	 * @return
	 */
	public static String randomString(int length)
	  {
	    Random random = new Random();
	    StringBuffer buffer = new StringBuffer(length);

	    for (int i = 0; i < length; ++i) {
	      int iRandom1 = random.nextInt(4);
	      if (iRandom1 != 3) {
	        int iRandom2 = random.nextInt(25);
	        buffer.append((char)(97 + iRandom2));
	      } else {
	        int iRandom2 = random.nextInt(9);
	        buffer.append((char)(48 + iRandom2));
	      }
	    }
	    return buffer.toString();
	  }

	  public static String toGBKString(String strT)
	  {
	    try
	    {
	      if (strT == null) {
	        return "";
	      }
	      String os = System.getProperty("os.name");
	      if ((os != null) && (os.toLowerCase().startsWith("windows"))) {
	        System.out.print("~~~~Windows System");
	      } else if ((os != null) && (os.toLowerCase().startsWith("aix"))) {
	        System.out.print("~~~~AIX System");
	      } else {
	        strT = new String(strT.getBytes("ISO8859_1"), "GBK");
	        System.out.print("~~~~Not Windows System");
	      }
	      return strT;
	    } catch (Exception e) {
	    }
	    return "";
	  }

	/**
	 * 去空格，去NULL
	 * @param str
	 * @return
	 */
	public static String trim(String str)
	  {
	    if ((str == null | "".equals(str))) {
	      return "";
	    }
	    return str.trim();
	  }

	  public static String compositeString(String[] strAry, String split)
	  {
	    return compositeString(strAry, split, true);
	  }

	  public static String compositeString(String[] strAry, String split, boolean removeNull) {
	    if ((strAry == null) || (strAry.length == 0)) {
	      return "";
	    }
	    StringBuffer retBuffer = new StringBuffer();
	    for (int i = 0; i < strAry.length; ++i) {
	      if ((!removeNull) || (!isNull(strAry[i]))) {
	        if (i > 0) {
	          retBuffer.append(split);
	        }
	        retBuffer.append(strAry[i]);
	      }
	    }
	    return retBuffer.toString();
	  }

	  public static int splitSignCount(String strSplit, String sign) {
	    String endStr = strSplit.substring(strSplit.length() - sign.length(), strSplit.length());
	    if (!endStr.equals(sign)) {
	      strSplit = strSplit + sign;
	    }

	    StringTokenizer ST = new StringTokenizer(strSplit, sign);
	    return ST.countTokens();
	  }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
	  public static boolean isEmpty(String str) {
	    boolean flag = false;
	    if ((str == null | "".equals(str))) {
	      flag = true;
	    }
	    return flag;
	  }

	/**
	 * 判断是否为空
	 * @param param
	 * @return
	 */
	public static boolean isNull(String param){
		return (param == null) || ("".equals(param));
	}

	/**
	 * 获取随机UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 数据替换
	 * @param content
	 * @param replaceMap
	 * @param sStr 开始字符
	 * @param eStr 结束字符
	 * @return
	 */
	public static String replaceCont(String content, Map<String, Object> replaceMap ,String sStr,String eStr){
		for(Map.Entry<String, Object> m : replaceMap.entrySet()){
			content = content.replace(sStr+m.getKey()+eStr,(String)m.getValue());
		}
		return content;
	}

	  public static String replaceAll(String str, String sep_str, String rep_str) {
	    if (str == null) {
	      return "";
	    }

	    int idx = str.indexOf(sep_str);
	    int sep_len = sep_str.length();

	    StringBuffer strBuff = new StringBuffer();
	    while (idx > -1) {
	      strBuff.append(str.substring(0, idx));
	      strBuff.append(rep_str);
	      str = str.substring(idx + sep_len);
	      idx = str.indexOf(sep_str);
	    }
	    strBuff.append(str);

	    return strBuff.toString();
	  }

	  public static String[] split(String str, String sep_str) {
	    StringTokenizer strToken = new StringTokenizer(str, sep_str);
	    int tokenCount = strToken.countTokens();

	    String[] str_list = new String[tokenCount];
	    for (int i = 0; i < tokenCount; ++i) {
	      str_list[i] = strToken.nextToken();
	    }

	    return str_list;
	  }

	  public static List split2List(String str, String sep_str)
	  {
	    StringTokenizer strToken = new StringTokenizer(str, sep_str);
	    int tokenCount = strToken.countTokens();

	    String[] str_array = new String[tokenCount];
	    for (int i = 0; i < tokenCount; ++i) {
	      str_array[i] = strToken.nextToken();
	    }
	    List str_list = Arrays.asList(str_array);
	    return str_list;
	  }

	  public static String[] split(String str, String sep_str, boolean sepflag)
	  {
	    int count = str.length() - replaceAll(str, sep_str, "").length() + 1;
	    if ((sepflag) && 
	      (str.endsWith(sep_str))) {
	      --count;
	    }

	    int sep_len = sep_str.length();
	    String[] str_list = new String[count];

	    for (int i = 0; i < count; ++i) {
	      int idx = str.indexOf(sep_str);
	      if (idx > -1) {
	        str_list[i] = str.substring(0, idx);
	        str = str.substring(idx + sep_len);
	      } else {
	        str_list[i] = str;
	        str = "";
	      }
	    }

	    return str_list;
	  }

	  public static String[] getMidStr(String str, String beg_str, String end_str) {
	    if ((str == null) || ("".equals(str))) {
	      return null;
	    }
	    if ((beg_str == null) || ("".equals(beg_str.trim())) || (end_str == null) || ("".equals(end_str.trim()))) {
	      return null;
	    }

	    int strlen = str.length();

	    int begstrlen = beg_str.length();
	    int endstrlen = end_str.length();

	    Vector vec = new Vector();

	    int beg_pos = str.indexOf(beg_str);
	    int end_pos = str.indexOf(end_str);

	    while ((str != null) && (beg_pos > -1) && (end_pos > -1) && (end_pos > beg_pos)) {
	      vec.add(str.substring(beg_pos + begstrlen, end_pos));
	      str = str.substring(end_pos + endstrlen);

	      if (str != null) {
	        beg_pos = str.indexOf(beg_str);
	        end_pos = str.indexOf(end_str);
	      } else {
	        beg_pos = -1;
	        end_pos = -1;
	      }
	    }

	    String[] array = new String[vec.size()];
	    for (int i = 0; i < vec.size(); ++i) {
	      array[i] = ((String)vec.get(i));
	    }

	    return array;
	  }



}
