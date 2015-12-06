/**
 * Created by whr on 12/2/15.
 */
import java.util.Stack;
public class ValidParentheses {
    public static boolean isValidParentheses(String s) {
        if(s == null || s.length() % 2 == 1) return false;
        Stack<Character> record = new Stack();
        record.push('*');
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ')' && record.peek() == '(') record.pop();
            else if(c == ']' && record.peek() == '[') record.pop();
            else if(c == '}' && record.peek() == '{') record.pop();
            else record.push(c);
        }
        return record.size() == 1;
    }



//    public static boolean isValid(String s) {
//        if(s == null || s.length() % 2 == 1) return false;
//        int count = 0;
//        for(int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if(c == '(') count++;
//            else {
//                if(count > 0) count--;
//                else return false;
//            }
//        }
//        return count == 0;
//    }
}
