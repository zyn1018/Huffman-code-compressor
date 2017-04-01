import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by yinan on 3/30/17.
 */
public class BinaryHeap {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private Node[] array;

    public BinaryHeap() {
        array = new Node[DEFAULT_CAPACITY];
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = new Node[capacity + 1];
    }

    public BinaryHeap(Node[] items) {
        currentSize = items.length;
        array = new Node[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (Node item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int hole) {
        int child;
        Node tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize
                    && array[child + 1].getPriority() < array[child].getPriority()) {
                child++;
            }
            if (array[child].getPriority() < tmp.getPriority()) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    public void insert(Node x) {
        if (isFull()) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        for (; hole > 1 && x.getPriority() <= array[hole / 2].getPriority(); hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }


    public boolean isFull() {
        return currentSize == array.length - 1;
    }


    public boolean isEmpty() {
        return currentSize == 0;
    }

    public Node findMin() {
        if (isEmpty())
            return null;
        return array[1];
    }


    public Node deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node minItem = findMin();
        array[1] = array[currentSize];
        array[currentSize--] = null;
        percolateDown(1);
        return minItem;
    }

    private void enlargeArray(int newSize) {
        Node[] old = array;
        array = new Node[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }

    public void printHeap() {
        for (Node anyType : array) {
            if (anyType == null) {
                continue;
            }
            System.out.print(anyType + " ");
        }
    }

    public void buildTreeUsingBinaryHeap(Map<String, Integer> map) {
        Node left_child;
        Node right_child;
        Node result;
        for (String c : map.keySet()) {
            if (c != null)
                insert(new Node(map.get(c), c, map.get(c)));

        }

        while (currentSize > 1) {
            left_child = deleteMin();
            right_child = deleteMin();
            result = left_child.add(right_child);
            result.setLeftChild(left_child);
            result.setRightChild(right_child);
            insert(result);

        }

    }
}
