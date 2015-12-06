/**
 * Created by whr on 12/2/15.
 */
public class RightRotation {
    public static int checkRightRotate (String word1, String word2) {
        if(word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0 || word1.length() != word2.length()) return -1;
        String s = word1 + word1;
        return s.indexOf(word2) == -1 ? -1 : 1;
    }
}
