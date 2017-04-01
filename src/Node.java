/**
 * Created by yinan on 3/28/17.
 */
public class Node {
    private int priority;
    private int frequence;
    private String str;
    private Node leftChild;
    private Node rightChild;
    private String bin;
    private Node parent;
    private Node child;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Node getSibling() {
        return sibling;
    }

    public void setSibling(Node sibling) {
        this.sibling = sibling;
    }

    private Node sibling;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public Node() {
    }

    public Node(String str) {
        this.str = str;
    }

    public Node(int priority, String str, int frequence) {
        this.priority = priority;
        this.str = str;
        this.frequence = frequence;
    }

    //add two nodes with lowest frequences
    public Node add(Node node) {
        Node n = new Node();
        n.priority = this.priority + node.priority;
        n.frequence = this.frequence + node.frequence;
        return n;
    }

    public boolean isLeaf(Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return true;
        } else
            return false;
    }

    public boolean less(Node node) {
        return this.priority < node.priority;
    }

    public String toString() {
        return String.valueOf(this.priority);
    }

}
