public class Solution {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        int []index = new int[2];
        index[0] = index[1] = 0;
        for (int i = 0; i < numbers.length - 1; i++){
            for (int j = i + 1; j < numbers.length; j++){
                if (target == numbers[i] + numbers[j]){
                    index[0] = i + 1;
                    index[1] = j + 1;
                    return index;
                }
            }
        }
        return index;
    }
}