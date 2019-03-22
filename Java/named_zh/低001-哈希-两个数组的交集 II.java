/*****
两个数组的交集 II

给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
**/
class Solution {
    /**
     * 使用集合实现
     */
    public int[] intersect_1(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        for (int num : nums1) {
            list1.add(num);
        }
        List<Integer> list2 = new ArrayList<>();
        for (int num : nums2) {
            if (list1.contains(num)) {
                list2.add(num);
                // 从 list1 除去已匹配的数值
                list1.remove(Integer.valueOf(num));
            }
        }
        int[] res = new int[list2.size()];
        int i = 0;
        for (int num : list2) {
            res[i++] = num;
        }
        return res;
    }

    /**
     * 使用集合的实现
     */
    public int[] intersect_2(int[] nums1, int[] nums2) {
        List<Integer> list1 = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2)
                .boxed()
                .filter(num -> {
                    if (list1.contains(num)) {
                        list1.remove(num);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        int[] res = new int[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            res[i] = list2.get(i);
        }
        return res;
    }

    /**
     * 使用映射实现
     */
    public int[] intersect_3(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        // 现将 nums1 出现的数值及频次放入映射中
        for (int num : nums1) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, ++count);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            // 获取映射中该数值出现的频次
            Integer count = map.get(num);
            if (count != null && count != 0) {
                list.add(num);
                // 注意每次匹配后，该数值的频次需要减 1（nums1 和 nums2 匹配的数值的频次要相同）
                map.put(num, --count);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 排序预处理
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {//当两者相等的时候将结果保存，两个数组的指针都增加
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}