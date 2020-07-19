import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yuping on 2020/7/12.
 */
public class Codec {

    public static  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //中序遍历也是一个序列话方案
        StringBuilder s = new StringBuilder();
        rserialize(root,s);
        return s.toString();

    }

    private void rserialize(TreeNode root,StringBuilder s) {
        //terminator
        if (root == null) {
            s.append("X");
            s.append(",");
            return;
        }else {
            s.append(root.val);
            s.append(",");
            rserialize(root.left,s);
            rserialize(root.right,s);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //根据中序遍历，类似层序遍历求解
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);

    }

    private TreeNode rdeserialize(List<String> l) {

        if (l.get(0).equals("X")) {
            l.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return  root;
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            String ret = new Codec().serialize(root);

            String out = (ret);

            System.out.print(out);

            root = new Codec().deserialize(ret);
        }
    }
}

