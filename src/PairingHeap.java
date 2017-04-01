import java.util.Map;

/**
 * Created by yinan on 3/30/17.
 */
public class PairingHeap {
    private Node root;

    private Node linkPair(Node first, Node second) {
        if (second == null) return first;
        if (first == null) return second;

        if (first.getPriority() < second.getPriority()) {
            //second is linked to first as a child

            //retain the sibling relation
            Node secondzSibling = second.getSibling();
            first.setSibling(secondzSibling);
            if (secondzSibling != null) secondzSibling.setParent(first);

            Node firstzChild = first.getChild();

            //update second's left and sibling pointers
            second.setParent(first);
            second.setSibling(firstzChild);

            //update first.child's pointer
            if (firstzChild != null) firstzChild.setParent(second);

            //update first's child
            first.setChild(second);
            return first;
        } else {
            //first is linked to second as a child

            //retain the sibling relation
            Node firstParent = first.getParent();
            second.setParent(firstParent);
            if (firstParent != null) {
                if (firstParent.getChild() == first) {
                    //firstParent is first's parent
                    firstParent.setChild(second);
                } else {
                    //firstParent is first's left sibling
                    firstParent.setSibling(second);
                }
            }

            Node secondzChild = second.getChild();
            //update first's left and sibling pointers
            first.setParent(second);
            first.setSibling(secondzChild);

            //update second's child pointer
            if (secondzChild != null) secondzChild.setParent(first);

            //update second's child
            second.setChild(first);
            return second;
        }
    }

    public Node insert(Node node) {
        if (root == null)
            root = node;
        else
            root = linkPair(node, root);
        return node;
    }

    public Node extractMin() {
        Node z = this.root;
        if (z != null) {
            if (z.getChild() == null)
                root = null;
            else {
                Node firstSibling = z.getChild();
                firstSibling.setParent(null);
                root = mergeSubHeaps(firstSibling);
            }
        }
        return z;
    }

    private Node mergeSubHeaps(Node firstSibling) {
        //the 1st pass: merge pairs from left side
        Node first = firstSibling;
        Node second = first.getSibling();

        Node tail = first;
        if (second != null) {
            tail = this.linkPair(first, second);
            first = tail.getSibling();
            if (first != null)
                second = first.getSibling();
            else
                second = null;
        }
        while (first != null && second != null) {
            tail = this.linkPair(first, second);
            first = tail.getSibling();
            if (first != null)
                second = first.getSibling();
            else
                second = null;
        }

        //the 2nd pass: merge pairs from right side
        if (first != null) {
            tail = first;
        }

        Node prev = tail.getParent();
        while (prev != null) {
            tail = this.linkPair(prev, tail);
            prev = tail.getParent();
        }
        return tail;
    }

    public void buildTreeUsingPairingHeap(Map<String, Integer> map) {
        Node left_child;
        Node right_child;
        Node result;
        for (String c : map.keySet()) {
            if (c != null)
                insert(new Node(map.get(c), c, map.get(c)));

        }

        while (root.getChild() != null) {
            left_child = extractMin();
            right_child = extractMin();
            result = left_child.add(right_child);
            result.setLeftChild(left_child);
            result.setRightChild(right_child);
            insert(result);

        }

    }
}
