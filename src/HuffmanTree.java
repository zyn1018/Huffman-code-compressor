import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinan on 3/28/17.
 */
public class HuffmanTree {
    //PriorityQueue
//    private BinaryHeap BinaryHeap;

    //text needed to be encoded;
    private String[] text;

    //frequence table
    public Map<String, Integer> freq_table;

    //code table
    public Map<String, String> result;

    public HuffmanTree(String[] text) {
        this.text = text;
        init();
    }

    private void init() {
        freq_table = new HashMap<String, Integer>();
        result = new HashMap<String, String>();
    }

    public void getStatistics() {
        int frequence;
        for (String str : text) {
            if (freq_table.containsKey(str)) {
                frequence = freq_table.get(str);
                frequence++;
                freq_table.put(str, frequence);
            } else {
                freq_table.put(str, 1);
            }
        }
    }

    //Build huffman tree

    public void encode(Node tree) {
        if (tree == null) {
            return;
        }
        Node left = tree.getLeftChild();
        Node right = tree.getRightChild();

        if (left != null) {
            left.setBin(tree.getBin() + "0");
            if (freq_table.containsKey(left.getStr())) {
                //if the node is in the freq_table, add it to the result table
                result.put(left.getStr(), left.getBin());
            }
        }

        if (right != null) {
            right.setBin(tree.getBin() + "1");
            if (freq_table.containsKey(right.getStr())) {
                //if the node is in the freq_table, add it to the result table
                result.put(right.getStr(), right.getBin());
            }
        }
        encode(left);
        encode(right);

    }

}
