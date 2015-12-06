/**
 * Created by whr on 12/2/15.
 */
public class LongestPalindrome {
    public static String getLongestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        if(s.length() == 1) return s;
        int startPos = 0, maxLen = 1, i = 0, n = s.length();
        while(i < n) {
            if(n - i <= maxLen / 2) break;
            int j = i, k = i;
            while (k < n - 1 && s.charAt(k + 1) == s.charAt(k)) k++; // Skip duplicate characters.
            i = k + 1;
            while (k < n - 1 && j > 0 && s.charAt(k + 1) == s.charAt(j - 1)) {
                k++;
                j--;
            } // Expand.
            if (k - j + 1 > maxLen) {
                startPos = j;
                maxLen = k - j + 1;
            }
        }
        return s.substring(startPos, startPos + maxLen);
    }
}
