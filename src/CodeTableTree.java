import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by yinan on 3/31/17.
 */
public class CodeTableTree {
    public Node root;

    public Node buildTreeUsingCodeTable(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        Set<String> binaryKey = map.keySet();
        for (String key : binaryKey) {
            int length = key.length();
            length = key.length();
            if (root == null) {
                root = new Node();
            }
            Node curNode = root;
            Node parentNode;
            for (int i = 0; i < length; i++) {
                if (i == length - 1) {
                    if (key.charAt(i) == '0') {
                        curNode.setLeftChild(new Node(map.get(key)));
                        break;
                    } else if (key.charAt(i) == '1') {
                        curNode.setRightChild(new Node(map.get(key)));
                        break;
                    }
                } else {
                    if (key.charAt(i) == '0') {
                        parentNode = curNode;
                        if (curNode.getLeftChild() == null) {
                            Node node = new Node();
                            parentNode.setLeftChild(node);
                            curNode = curNode.getLeftChild();

                        } else {
                            curNode = curNode.getLeftChild();
                        }
                    } else if (key.charAt(i) == '1') {
                        parentNode = curNode;
                        if (curNode.getRightChild() == null) {
                            Node node = new Node();
                            parentNode.setRightChild(node);
                            curNode = curNode.getRightChild();
                        } else {
                            curNode = curNode.getRightChild();
                        }
                    }
                }

            }
        }
        return root;
    }

    public void printTree(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curnode = queue.poll();
            System.out.println(curnode.getStr());
            if (curnode.getLeftChild() != null) {
                queue.add(curnode.getLeftChild());
            }
            if (curnode.getRightChild() != null) {
                queue.add(curnode.getRightChild());
            }
        }
    }
}