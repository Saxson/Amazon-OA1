/**
 * Created by whr on 12/5/15.
 */
public class DaysChange {
    public static int[] flip(int[] input, int days) {
        if(input == null || input.length == 0 || days <= 0) return input;
        int[] res = input;
        for(int i = 0 ; i < days; i++) {
            int pre = 0;
            for(int j = 0; j < input.length; j++) {
                int temp = input[j];
                if(j + 1 < input.length) res[j] = pre ^ res[j + 1];
                else res[j] = pre ^ 0;
                pre = temp;
            }
        }
        return res;
    }
//    public static void main(String[] args) {
//        int[] input = {0,0,1,1,0,1};
//        int[] res = flip(input, 1);
//        for(int n : res) {
//            System.out.print(n + " ");
//        }
//    }
}
