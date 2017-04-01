import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by yinan on 3/29/17.
 */
public class FourWayHeap {

    private ArrayList<Node> heap;

    public FourWayHeap() {
        // TODO Auto-generated constructor stub
        heap = new ArrayList<>();
    }

    protected FourWayHeap(int sign, Collection<Node> contents) {
        heap = new ArrayList<Node>(contents);
        for (int i = 1 + heap.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    private final int parent(int i) {
        return (int) Math.ceil((i - 1) / 4);
    }

    //Return null in List if children are absent.
    private final ArrayList<Node> children(int i) {
        ArrayList<Node> child = new ArrayList<Node>(4);
        try {
            child.add(heap.get((4 * (i - 1)) + 2));
            child.add(heap.get((4 * (i - 1)) + 3));
            child.add(heap.get((4 * (i - 1)) + 4));
            child.add(heap.get((4 * (i - 1)) + 5));
        } catch (Exception e) {
            //child = null;
        }

        return child;
    }

    private final int firstChild(int i) {
        return (4 * (i)) + 2;
    }

    public int size() {
        return heap.size();
    }


    private void swap(int i, int j) {
        Node temp = heap.get(j);
        heap.set(j, heap.get(i));
        heap.set(i, temp);
    }

    private void heapify(int i) {
        int j = 1;
        int first = firstChild(i);
        int second = first + 1;
        int third = second + 1;
        int fourth = third + 1;

        //Check for all 4
        if (first < heap.size() && (heap.get(first).getPriority() < heap.get(i).getPriority()))
            j = first;
        if (second < heap.size() && (heap.get(second).getPriority() < heap.get(i).getPriority()))
            j = second;
        if (third < heap.size() && (heap.get(third).getPriority() < heap.get(i).getPriority()))
            j = third;
        if (fourth < heap.size() && (heap.get(fourth).getPriority() < heap.get(i).getPriority()))
            j = fourth;

        if (i != j) {
            swap(i, j);
            heapify(j);
        }

    }

    public void insert(Node t) {
        heap.add(t);
        int i = heap.size() - 1;
        int p = parent(i);
        while (i > 0 && (heap.get(i).getPriority() < heap.get(p).getPriority())) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }

    public Node remove() {
        Node ans = null;
        if (heap.size() > 0) {
            ans = heap.remove(0);
            if (heap.size() > 1) {
                heap.add(0, heap.remove(heap.size() - 1));
                heapify(0);
            }
        }
        return ans;
    }

    public void buildTreeUsingFourWayHeap(Map<String, Integer> map) {
        Node left_child;
        Node right_child;
        Node result;
        for (String c : map.keySet()) {
            if (c != null)
                insert(new Node(map.get(c), c, map.get(c)));

        }

        while (heap.size() > 2) {
            left_child = remove();
            right_child = remove();
            result = left_child.add(right_child);
            result.setLeftChild(left_child);
            result.setRightChild(right_child);
            insert(result);

        }

    }

    @Override
    public String toString() {
        return heap.toString();
    }
}

