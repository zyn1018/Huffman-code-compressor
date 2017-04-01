/**
 * Created by yinan on 3/29/17.
 */
public class TestStructures {
    public static void main(String[] args) {

        InputReader ir = new InputReader("sample_input_large.txt");
        String[] text = ir.parseInput();
        HuffmanTree huffmanTree = new HuffmanTree(text);
        huffmanTree.getStatistics();
        /*
         *  test binary heap
         */
//        BinaryHeap mheap = new BinaryHeap();
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//        mheap.buildTreeUsingBinaryHeap(huffmanTree.freq_table);
//        }
//        long end1 = System.currentTimeMillis();
//        System.out.println("Total Time for using binary heap to build huffman tree 10 times = " + (end1 - start1) + "ms");
//        System.out.println("Average time for each round is " + (end1 - start1) / 10 + "ms");



        /*
         *  test 4-way heap
         */
//        FourWayHeap fHeap = new FourWayHeap();
//        long start = System.currentTimeMillis();
//        fHeap.buildTreeUsingFourWayHeap(huffmanTree.freq_table);
//        Node root = fHeap.remove();
//        root.setBin("");
//        huffmanTree.encode(root);
//        System.out.println(huffmanTree.result);
//        long end = System.currentTimeMillis();
//        System.out.println("Time using 4-way heap: " + (end - start) + "ms");


        /*
         *  test pairing heap
         */
        PairingHeap pairingHeap = new PairingHeap();
        long start3 = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
            pairingHeap.buildTreeUsingPairingHeap(huffmanTree.freq_table);
//        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total Time for using pairing heap to build huffman tree 10 times = " + (end3 - start3) + "ms");
//        System.out.println("Average time for each round is " + (end3 - start3) / 10 + "ms");

    }
}
