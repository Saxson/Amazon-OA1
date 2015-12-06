/**
 * Created by whr on 12/5/15.
 */
public class ArithmeticSequence {
    public static int getSlices(int[] nums) {
        int count = 0, start = 0;
        while(start < nums.length - 2) {
            int diff = nums[start + 1] - nums[start];
            int end = start + 2;
            while(end < nums.length) {
                if(nums[end] - nums[end - 1] == diff) {
                    count += end - start - 1;
                    end++;
                } else {
                    break;
                }
            }
            start = end - 1;
        }
        return count;
    }
//方法2
    public static int Solution(int[] array) {
        if (array == null || array.length < 3)	return 0;
        int rvalue = 0, gap = array[1] - array[0], length = 2;
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i+1] - array[i] == gap)	length++;
            else {
                gap = array[i+1] - array[i];
                if (length >= 3) rvalue += (length - 1) * (length - 2) / 2;
                if (rvalue > 1000000000) return -1;
                length = 2;
            }
        }
        if (length >= 3)
            rvalue += (length - 1) * (length - 2) / 2;
        return rvalue > 1000000000 ? -1 : rvalue;
    }

    public static void main(String[] args) {
        int[] input = {-1,1,3,3,3,2,1,0};
        System.out.println(Solution(input));
    }

}
