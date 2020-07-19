/**
 * Created by yuping on 2020/7/12.
 */
public class CommonAncestor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //terminator
        if (root == null || root == p || root == q) {
            return root;
        }

        //drill down
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return root;

    }
}
