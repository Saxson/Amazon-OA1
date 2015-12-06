/**
 * Created by whr on 12/5/15.
 */
public class TreeAmplitude {

    public static int getAmplitude(TreeNode root) {//可能要改成long型
        if(root == null) return 0;
        return helper(root, root.val, root.val);
    }
    public static int helper(TreeNode root, int min, int max) {
        if(root == null) return max - min;
        min = Math.min(min,root.val);
        max = Math.max(max,root.val);
        return Math.max(helper(root.left, min, max), helper(root.right, min, max));
    }

}
