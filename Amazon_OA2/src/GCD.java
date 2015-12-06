/**
 * Created by whr on 12/4/15.
 */
public class GCD {
    public static int getGCD(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int res = nums[0];
        if(nums.length == 1) return res;
        for(int i = 1; i < nums.length; i++) {
            res = gcd(res,nums[i]);
        }
        return res;
    }
    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
