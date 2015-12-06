/**
 * Created by whr on 12/2/15.
 */
public class RemoveVowels {
    public static String removeVowel(String s, String v) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(v.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
