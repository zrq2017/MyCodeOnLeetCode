package algorithm;

/**
 * Created by zrq on 2019-5-25.
 */
public class ReverseWords {
    /**
     * 反转字符串中的单词 III
     *
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例 1:
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc"
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     * @param args
     */
    public static void main(String[] args) {
        String s="Let's take LeetCode contest";
        reverseWords(s);
    }

    //双指针解法：一个指向当前单词头，一个顺序往后走，碰到空格做单词的翻转
    public static String reverseWords(String s) {
        int len=s.length(),p=0,q=0;//p为移动指针，q为上一个空格后的位置指针
        StringBuilder sb=new StringBuilder();
        while(p<len){
            if(s.charAt(p)==' '){
                for(int i=p-1;i>=q;i--){
                    sb.append(s.charAt(i));
                }
                q=p+1;//将q移到下一个单词开始位置
                sb.append(s.charAt(p++));
            }
            p++;
        }
        if(q<len){//将最后一个单词加入，因为最后一个单词不是以空格分割为结尾的
            for(int i=len-1;i>=q;i--){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    //普通解法：翻转字符串后按空格分割然后翻转单个单词
    public static String reverseWordsEasy(String s) {
        StringBuilder sb=new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){//翻转整个字符串
            sb.append(s.charAt(i));
        }
        String[] t=sb.toString().split(" ");//按空格分割
        sb=new StringBuilder();
        int index=t.length-1;//保存分割后数组长度
        for(int i=index;i>=0;i--){//逆序添加字符串
            if(i!=index){
                sb.append(' ');
            }
            sb.append(t[i]);
        }
        return sb.toString();
    }
}
