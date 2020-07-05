import java.util.*;

/**
 * Created by yuping on 2020/7/5.
 */
public class NTreePreOrder {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    public  List<Integer> preOrderMethodOne(Node root) {
           List<Integer> ans = new ArrayList();
           recursion(root,ans);
           return ans;

    }

    public List<Integer> preorder(Node root) {

        //迭代，放入栈中  前序遍历顺序为 根 从左到右的叶子节点，所以入栈应该是从右到左的节点才对，所以添加node时候需要反转
        List<Integer> ans = new ArrayList();
        Deque<Node> stack = new ArrayDeque();//栈推荐用这种写法
        if (root == null) {
            return ans;
        }
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            ans.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.addFirst(item);
            }
        }
        return ans;


    }

    public void recursion(Node root,List<Integer> list){
        //递归
        if (root == null) {
            return ;
        }
        list.add(root.val);
        if (root.children != null) {
            for (Node node : root.children) {
                recursion(node,list);
            }
        }
    }






}
