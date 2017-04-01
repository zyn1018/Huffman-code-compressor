import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinan on 3/31/17.
 */
public class decoder {
    public static void main(String[] args) {
        File fileEncoded = new File("encoded.bin");
        File fileCodeTab = new File("code_table.txt");
        Map<String, String> binaryMap = new HashMap<>();
        CodeTableTree cTree = new CodeTableTree();
        if (!fileEncoded.exists()) {
            try {
                fileEncoded.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!fileCodeTab.exists()) {
            try {
                fileCodeTab.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long start = System.currentTimeMillis();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileCodeTab)));
            String[] lineTokens;
            String line;
            while ((line = br.readLine()) != null) {
                lineTokens = line.split(" ");
                binaryMap.put(lineTokens[1], lineTokens[0]);

            }
//            System.out.println(binaryMap);
//            Node root = cTree.buildTreeUsingCodeTable(binaryMap);
//            cTree.printTree(root);
            BinaryInputReader bir = new BinaryInputReader("encoded.bin");

            try {
                File output = new File("decoded.txt");

                String code = "";

                if (!output.exists())
                    output.createNewFile();

                BufferedWriter writer = new BufferedWriter(new FileWriter(output));

                // While there is still more to read
                // Read bits and add that to the code
                while (!bir.isEmpty()) {
                    boolean bit = bir.readBit();
                    if (bit)
                        code += "1";
                    else
                        code += "0";
                    // Attempt to match the code in the hashmap if it is write the code
                    if (binaryMap.containsKey(code)) {
                        writer.write(binaryMap.get(code));
                        writer.newLine();
                        code = "";
                    }
                }
                bir.input.close();
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Failed writing data to file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Decoding takes " + (end - start) + "ms");
    }
}
