import java.util.Arrays;

/**
 * Created by whr on 12/5/15.
 */
public class FourInteger {
    public static int[] getMaxF(int A, int B, int C, int D) {
        int[] res = new int[4];
        res[0] = A;
        res[1] = B;
        res[2] = C;
        res[3] = D;
        Arrays.sort(res);
        swap(res, 0, 1);
        swap(res, 2, 3);
        swap(res, 0, 3);
        return res;
    }
    public static void swap(int[] res, int i, int j) {
        int temp = res[i];
        res[i] = res[j];
        res[j] = temp;
    }

//    public static void main(String[] args) {
//        int[] res = getMaxF(1,2,3,4);
//        for(int n : res) {
//            System.out.print(n + " ");
//        }
//    }
}
