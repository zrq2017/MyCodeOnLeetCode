package named_en;

import java.util.regex.Matcher;  
import java.util.regex.Pattern; 
public class Test {
	public static String changeOperator(String str)
	{
		//中国电信
		String CHINA_TELECOM_PATTERN = "(^1(33|53|77|8[019])\\d{1}$)|(^1700$)";
		//中国联通
		String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{1}$)|(^1709$)";
		//中国移动
		String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{1}$)|(^1705$)";
		String[] pat={CHINA_TELECOM_PATTERN,CHINA_UNICOM_PATTERN,CHINA_MOBILE_PATTERN};
		String[] name={"中国电信","中国联通","中国移动"};
		Pattern pattern = null;  
	    Matcher match = null;
	    for(int i=0;i<3;i++){
	    	pattern=Pattern.compile(pat[i]);
	    	match=pattern.matcher(str);
	    	if(match.find()){
	    		return name[i];
	    	}
	    }
		return "未知";
//		String result = "";
//		if(str.substring(0,4).equals("1705") || str.substring(0,3).equals("134") ||  str.substring(0,3).equals("135") || str.substring(0,3).equals("136") || str.substring(0,3).equals("137")  || str.substring(0,3).equals("182")|| str.substring(0,3).equals("138")  || str.substring(0,3).equals("139") ||  str.substring(0,3).equals("150") || str.substring(0,3).equals("151") || str.substring(0,3).equals("152")|| str.substring(0,3).equals("184")  || str.substring(0,3).equals("183")|| str.substring(0,3).equals("157") || str.substring(0,3).equals("158") || str.substring(0,3).equals("159") || str.substring(0,3).equals("187") || str.substring(0,3).equals("188")|| str.substring(0,3).equals("147")|| str.substring(0,3).equals("182")|| str.substring(0,3).equals("148")|| str.substring(0,3).equals("165") || str.substring(0,3).equals("172")|| str.substring(0,3).equals("178")|| str.substring(0,3).equals("198")){
//				result="中国移动";	//中国移动
//		}
//		else if(str.substring(0,4).equals("1709") || str.substring(0,3).equals("130") ||  str.substring(0,3).equals("131") || str.substring(0,3).equals("132") || str.substring(0,3).equals("156")  || str.substring(0,3).equals("155")|| str.substring(0,3).equals("166")|| str.substring(0,3).equals("171")|| str.substring(0,3).equals("175")|| str.substring(0,3).equals("185")  || str.substring(0,3).equals("186")||  str.substring(0,3).equals("145") ||str.substring(0,3).equals("146")|| str.substring(0,3).equals("176")){
//				result="中国联通";	//中国联通
//		}
//		else if(str.substring(0,4).equals("1700") || str.substring(0,3).equals("133") ||  str.substring(0,3).equals("153") || str.substring(0,3).equals("180")|| str.substring(0,3).equals("181")|| str.substring(0,3).equals("199") || str.substring(0,3).equals("189")|| str.substring(0,3).equals("174")|| str.substring(0,3).equals("173")|| str.substring(0,3).equals("149")|| str.substring(0,3).equals("177")){
//				result="中国电信";	//中国电信
//		}
//		else{
//			result="未知运营商";	//未知运营商
//		}
//		return result;
	}
	public static void main(String[] args) {
		String str=Test.changeOperator("1511");
		System.out.println(str);
	}
}
