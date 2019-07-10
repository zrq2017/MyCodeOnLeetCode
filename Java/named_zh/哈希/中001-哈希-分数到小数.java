/*****
分数到小数

给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。

如果小数部分为循环小数，则将循环的部分括在括号内。

示例 1:

输入: numerator = 1, denominator = 2
输出: "0.5"
示例 2:

输入: numerator = 2, denominator = 1
输出: "2"
示例 3:

输入: numerator = 2, denominator = 3
输出: "0.(6)"
**/
/**实现除法运算，结果返回为String类型的浮点数。
 * 必须要考虑的就是循环小数，当余数跟之前重复的时候就出现了循环小数
 * 可以根据这个来设计我们的算法*/
public String fractionToDecimal(int numerator, int denominator) {

   /**首先考虑除数为0，被除数为0的特殊情况*/
   if(numerator==0) return "0";
   if(denominator==0) return String.valueOf(Integer.MAX_VALUE);
   
   /**其次考虑两个数的符号不一致的情况*/
   String res=new String();
   if((numerator<0)^(denominator<0))
	   res=res+"-";
   
   /**考虑到Int类型为-2^32的溢出，所以转化为long，一定要先转换再求绝对值
	* 既然已经考虑了符号，就可以直接转为绝对值**/
   long num=Math.abs((long)numerator);
   long den=Math.abs((long)denominator);
   
   /**区别整数部分和小数部分*/
   long ren=num/den;
   long rem=num%den;
   res=res+String.valueOf(ren);
   
   /**没有小数部分，直接返回**/
   if(rem==0) return res;
   
   
   res+='.';
   /**采用map来存储余数，以及该余数对应的小数的位置，这样方便我们为循环小数打括号*/
   Map<Long,Integer> map=new HashMap<Long,Integer>();
   int beg=res.length();
   while(rem>0)
   {
	   rem=rem*10;
	   ren=rem/den;
	   /**如果出现重复，需要截取出循环的部分打括号**/
	   if(map.containsKey(rem))
	   {
		   /**循环前**/
		   String part1=res.substring(0,map.get(rem));
		   /** 循环后*/
		   String part2=res.substring(map.get(rem));
		   res=part1+"("+part2+")";
		   break;
	   }
	   else
	   {
		   res+=String.valueOf(ren);
		   map.put(rem, beg);
	   }
	   /**更新位置计数和余数**/
	   beg++;
	   rem=rem%den;
	   
   }
   
	 return res;  
	
}
/**
* 方法2
**/
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        if(numerator < 0&&denominator>0||numerator>0&&denominator<0){
            stringBuilder.append("-");
        }
        long num=Math.abs((long)numerator);
        long den=Math.abs((long)denominator);
        long integer = num / den;
        stringBuilder.append(integer);
        if(0 == num % den){
            return stringBuilder.toString();
        }
        stringBuilder.append(".");
        StringBuilder decimal = new StringBuilder();
        num -= integer * den;
        HashMap hashMap = new HashMap<>();    //键代表的是被除数，值代表的是余数
        HashSet hashSet = new HashSet<>(); //存储重复的被除数
        StringBuilder loop = new StringBuilder();
        while(0 != num){
            decimal.append(num * 10 / den);
            hashMap.put(num, num * 10 % den);
            num = (long) hashMap.get(num);
            if(hashMap.containsKey(num)){
                if(hashSet.contains(num)){
                    break;
                }else{
                    loop.append(num * 10 / den);
                    hashSet.add(num);
                }

            }
        }
        if(0 != num){
            return stringBuilder.toString() + decimal.substring(0, decimal.indexOf(loop.toString())) + "(" + loop + ")";
        }else{
            return stringBuilder.append(decimal).toString();
        }
    }
}