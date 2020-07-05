import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yuping on 2020/7/5.
 */
public class LevelOrder {


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




    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList();

        if (root == null) {
            return ans;
        }

        Queue<Node> queue = new LinkedList();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                if (node.children != null) {
                    queue.addAll(node.children);
                }
            }
            ans.add(level);
        }

        return ans;
    }




}
