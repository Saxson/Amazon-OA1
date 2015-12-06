/**
 * Created by whr on 12/2/15.
 */
import java.util.ArrayList;
import java.util.List;

public class WindowSum {
    public static List<Integer> getWindowSum(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length < k || k <= 0) return res;
        int sum = 0;
        for(int i = 0;i < nums.length; i++) {
            if(i < k) {
                sum += nums[i];
            } else {
                System.out.println("hah");
            }
        }
        res.add(sum);
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {4,2,73,11,-5};
        List<Integer> res = getWindowSum(nums, 3);
        for(int i : res) System.out.print(" " + i);
    }
}
