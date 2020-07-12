import java.util.HashMap;

/**
 * Created by yuping on 2020/7/12.
 */
public class ConstructTreeFromPreOrderAndInOrder {


     // Definition for a binary tree node.
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //中序遍历  前序遍历
        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }

        return buildTreeRecursive(preorder,0,preorder.length,0,inorder.length,map);

    }

    private TreeNode buildTreeRecursive(int[] preorder,int preStart,int preEnd, int inStart, int inEnd,HashMap<Integer,Integer> map) {

        //terminate
        if (inStart >= inEnd) {
            return null;
        }

        //获取根节点
        int root = preorder[preStart];
        TreeNode node = new TreeNode(root);
        int pos = map.get(root);
        int leftTreeLen = pos-inStart;
        //递归的遍历左右中序节点
        node.left = buildTreeRecursive(preorder, preStart+1, preStart+leftTreeLen+1, inStart,pos,map);
        node.right = buildTreeRecursive(preorder, preStart+leftTreeLen+1,preEnd,pos+1, inEnd,map);

        return node;
    }


}
