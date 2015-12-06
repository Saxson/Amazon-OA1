/**
 * Created by whr on 12/2/15.
 */
import java.util.Deque;
import java.util.ArrayDeque;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}
public class SubTree {
    public static boolean check(TreeNode p, TreeNode q) {
        if(q == null) return true;
        if(p == null) return false;
        return (p.val == q.val && isSameTree(p, q)) || check(p.left, q) || check(p.right, q);
    }

    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        return root1.val == root2.val && isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

}
