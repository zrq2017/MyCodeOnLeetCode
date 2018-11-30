package named_zh;

import java.util.Scanner;

import named_en.RemoveDuplicates;

/**
 * 题目：给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * 示例 1:
 * 
 * 给定数组 nums = [1,1,2],
 * 
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。 示例 2:
 * 
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。
 **/

public class 排序数组重复元素删除 {
	public static void main(String[] args) {
		RemoveDuplicates instances = new RemoveDuplicates();
		Scanner input = new Scanner(System.in);
		System.out.print("请输入数组长度：");
		int n = input.nextInt();
		int[] nums = new int[n];
		System.out.print("请输入数组：");
		for (int i = 0; i < n; i++) {
			nums[i] = input.nextInt();
		}
		int len = 0;
		System.out.print("请选择方法(1-3)：");
		int choose = input.nextInt();
		long start = System.currentTimeMillis();
		switch (choose) {
		case 1:
			len = instances.removeDuplicates1(nums);
			break;
		case 2:
			len = instances.removeDuplicates2(nums);
			break;
		case 3:
			len = instances.removeDuplicates3(nums);
			break;
		}
		long end = System.currentTimeMillis();
		System.out.print("数组长度：" + len + " :");
		for (int i = 0; i < len; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.print("\n用时：" + (end - start) + "ms");
	}

	/**
	 * 方法一：一层循环：边扫描边移动指针，i表示当前到了那个位置，j表示比较到了那个位置
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates1(int[] nums) {
		int len = nums.length;
		if (len == 0 || len == 1)
			return len;
		int i = 0;
		for (int j = 1; j < len; j++) {
			if (nums[i] != nums[j]) {// 不等就指针前行
				i++;
				nums[i] = nums[j];// i==j则等同不移动，i<j则移动
			} // 等的话就让指针j一直向后遍历
		}
		return i + 1;
	}

	/**
	 * 方法二：一层循环：第一层做用i顺序指针遍历所有元素，指针j做每个元素比较，第二层去除重复元素
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates2(int[] nums) {
		int len = nums.length;
		if (len == 0 || len == 1)
			return len;
		int i = 0, j = 1;
		while (i < len) {// i指针控制顺序往后移动，j指针控制i后的元素与i比较
			if (nums[i] == nums[j] && j <= len - 1) {
				for (int k = j; k < len - 1; k++) {// 去除重复元素
					nums[k] = nums[k + 1];
				}
				len--;
			} else
				j++;
			if (j == len) {
				i++;
				j = i + 1;
			} else if (j > len)
				break;
		}
		return len;
	}

	/**
	 * 方法三：三层循环：第一层做顺序指针遍历所有元素，第二层做每个元素比较，第三层去除重复元素
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates3(int[] nums) {
		int len = nums.length;
		if (len == 0 || len == 1)
			return len;
		for (int i = 0; i < len; i++) {// 遍历每个元素
			for (int j = i + 1; j < len; j++) {// 每个当前元素之后的元素与当前元素比较
				if (nums[i] == nums[j]) {
					for (int k = j; k < len - 1; k++) {// 去除相等时的元素，整体往前移动
						nums[k] = nums[k + 1];
					}
					len--;
				}
			}
		}
		return len;
	}
}
