/*****
根据身高重建队列

假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

**/
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people==null||people.length==0||people[0].length==0) return new int[0][0]; 
        Arrays.sort(people,(a,b)->(a[0]==b[0]?a[1]-b[1]:b[0]-a[0]));
        List<int[]> list=new ArrayList<>();
        for(int i=0;i<people.length;i++){
            list.add(people[i][1],people[i]);
        }
        return list.toArray(new int[people.length][]);
    }
}