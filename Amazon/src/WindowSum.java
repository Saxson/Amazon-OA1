/**
 * Created by whr on 12/2/15.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class WindowSum {
    public static List<Integer> getWindowSum(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.length < k || k <= 0) return res;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(i < k) {
                sum += nums[i];
            } else {
                res.add(sum);
                sum -= nums[i - k];
                sum += nums[i];
            }
        }
        res.add(sum);
        return res;
    }

    public static List<Integer> getWinSum(List<Integer> nums, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.size() < k || k <= 0) return res;
        int sum = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(i < k) {
                sum += nums.get(i);
            } else {
                res.add(sum);
                sum -= nums.get(i-k);
                sum += nums.get(i);
            }
        }
        res.add(sum);
        return res;
    }
}
