import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by yuping on 2020/7/5.
 */
public class TwoTreePreOrder {


      // Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    public List<Integer> preorderTraversalMethodOne(TreeNode root) {
        //递归
         List<Integer> ans = new ArrayList();
         recursionPreOrder(root,ans);
         return ans;

    }


    public List<Integer> preorderTraversal(TreeNode root) {

        //迭代,为了确保根 左右的顺序，入栈先入右后入左，这样才能出栈先左后右
        Deque<TreeNode> stack = new ArrayDeque();
        List<Integer> ans = new ArrayList();
        if (root == null) {
            return ans;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            ans.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return ans;


    }

    private void recursionPreOrder(TreeNode root,List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        recursionPreOrder(root.left,list);
        recursionPreOrder(root.right,list);
    }



}
