/**
 * Created by whr on 12/4/15.
 */
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}
public class MinimumPathSum {
    public static int findMinSum(TreeNode root) {
        if(root == null) return 0;
        int[] res = {Integer.MAX_VALUE};
        findPath(root, 0, res);
        return res[0];
    }
    public static void findPath(TreeNode root, int sum, int[] res) {
        if(root.left == null && root.right == null) {
            res[0] = Math.min(res[0], sum + root.val);
            return;
        }
        int curSum = sum + root.val;
        if(root.left != null) findPath(root.left, curSum, res);
        if(root.right != null) findPath(root.right, curSum,res);
    }

    //如果题目说不包含负数，可以剪枝
    public static void findPath2(TreeNode root, int sum, int[] res) {
        if(root.left == null && root.right == null) {
            res[0] = Math.min(res[0], sum + root.val);
            return;
        }
        int curSum = sum + root.val;
        if(root.left != null && curSum < res[0]) findPath2(root.left, curSum, res);
        if(root.right != null && curSum < res[0]) findPath2(root.right, curSum,res);
    }
}
