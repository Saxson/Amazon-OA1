/**
 * Created by whr on 12/4/15.
 */
import java.util.*;

public class WindowMinimum {
    public static List<Integer> getWindowMinimum(ArrayList<Integer> nums, int k) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums == null || nums.size() < k || k <= 0) return res;
        Deque<Integer> window = new ArrayDeque<Integer>();
        for(int i = 0; i < nums.size(); i++) {
            if(!window.isEmpty() && window.getFirst() <= i - k) window.removeFirst();
            while(!window.isEmpty() && nums.get(window.getLast()) >= nums.get(i)) window.removeLast();
            window.addLast(i);
            if(i >= k-1) res.add(nums.get(window.getFirst()));
        }
        return res;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(4, 2, 12, 11, -5));
//        List<Integer> res = getWindowMinimum(nums,2);
//        for(int i : res) {
//            System.out.print(i + " ");
//        }
//    }
}
