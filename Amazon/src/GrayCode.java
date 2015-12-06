/**
 * Created by whr on 12/2/15.
 */
public class GrayCode {
    public static int grayCheck(byte n1, byte n2) {
        byte x = (byte)(n1 ^ n2);
        int count = 0;
        while(x != 0) {
            x = (byte)(x & (x-1));
            count++;
        }
        return count == 1 ? 1 : 0;
    }


    public static int grayCheck2(byte n1, byte n2) {
        byte x = (byte)(n1 ^ n2);
        if(x != 0 && (byte)(x&(x-1)) == 0) return 1;
        else return 0;
    }
}
