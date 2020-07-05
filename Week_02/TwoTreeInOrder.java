import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by yuping on 2020/7/5.
 */
public class TwoTreeInOrder {


      //Definition for a binary tree node.
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    public List<Integer> inorderTraversalMethodOne(TreeNode root) {
         //递归
         List<Integer> res = new ArrayList();
         inorderTraversalInternal(root,res);

         return res;

    }



    public List<Integer> inorderTraversal(TreeNode root) {


        //自己模范递归，写一个栈, 中序遍历的顺序为 左 根 右
        List<Integer> ans = new ArrayList();
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            while(cur!=null){
                stack.addFirst(cur);
                cur = cur.left;
            }

            cur = stack.removeFirst();
            //弹出栈顶元素 开始进行处理，输出值，如果有右节点，添加右节点
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;


    }

    public void inorderTraversalInternal(TreeNode root,List<Integer> list) {
        if (root == null ) {
            return;
        }
        inorderTraversalInternal(root.left,list);
        list.add(root.val);
        inorderTraversalInternal(root.right,list);
    }

}
