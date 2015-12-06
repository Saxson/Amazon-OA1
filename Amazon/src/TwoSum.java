/**
 * Created by whr on 12/2/15.
 */
import java.util.HashMap;
public class TwoSum {
    public static int countTwoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2) return 0;
        int count = 0, diff = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int n : nums) {
            diff = target - n;
            if(map.containsKey(diff)) {
                count += map.get(diff);
            }
            if(map.containsKey(n)) {
                map.put(n, map.get(n)+1);
            } else {
                map.put(n,1);
            }
        }
        return count;
    }
}
