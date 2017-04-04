import java.io.*;

/**
 * Created by yinan on 3/28/17.
 */
public class encoder {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int currentBit = 0;
        InputReader ir = new InputReader("sample_input_large.txt");
        String[] text = ir.parseInput();
        HuffmanTree huffmanTree = new HuffmanTree(text);
        huffmanTree.getStatistics();
//        PairingHeap pairingHeap = new PairingHeap();
//        pairingHeap.buildTreeUsingPairingHeap(huffmanTree.freq_table);
//        Node root = pairingHeap.extractMin();
        BinaryHeap bh = new BinaryHeap();
        bh.buildTreeUsingBinaryHeap(huffmanTree.freq_table);
        Node root = bh.deleteMin();
        root.setBin("");
        huffmanTree.encode(root);
        String currentString = "";
        String subByte = "";
        byte writebyte = 0;
        File file = new File("code_table.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (String str : huffmanTree.result.keySet()) {
                bw.write(str + " " + huffmanTree.result.get(str));
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File binfile = new File("encoded.bin");
        if (!binfile.exists()) {
            try {
                binfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            DataOutputStream binWriter = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(binfile)));
            for (String str : text) {
                String BinaryString = huffmanTree.result.get(str);
                currentString += BinaryString;
                if (currentString.length() > 8) {
                    while (currentString.length() > 8) {
                        subByte = currentString.substring(0, 8);
                        currentString = currentString.substring(8);
                        writebyte = Integer.valueOf(subByte, 2).byteValue();
                        binWriter.write(writebyte);
                    }
                }

            }
            if (currentString.length() != 0) {
                int num = 8 - currentString.length();
                for (int i = 0; i < num; i++) {
                    currentString += "0";
                }
                writebyte = Integer.valueOf(currentString, 2).byteValue();
                binWriter.write(writebyte);

            }

            binWriter.flush();
            binWriter.close();
            long end = System.currentTimeMillis();
            System.out.println("Encoding takes " + (end - start) + "ms");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
