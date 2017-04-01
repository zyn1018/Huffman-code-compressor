import java.io.*;

/**
 * Created by yinan on 3/31/17.
 */
public class BinaryInputReader {
    public BufferedInputStream input;
    public int byteToRead;
    public int bitsRemaning;

    /**
     * Constructor
     */
    public BinaryInputReader(String inputFile) throws FileNotFoundException {
        File file = new File(inputFile);
        this.input = new BufferedInputStream(new FileInputStream(file));
        if (!file.exists()) {
            System.out.println("Input file does not exist");
            System.exit(0);
        }
        fillBits();
    }

    /**
     * Reads a single character from the file
     */
    private void fillBits() {
        try {
            byteToRead = input.read();
            bitsRemaning = 8;
        } catch (IOException e) {
            byteToRead = -1;
            bitsRemaning = -1;
        }
    }

    /**
     * Reads a single bit a time and if there are no more bits it fills again
     *
     * @return true or false depending on the bit
     */
    public boolean readBit() {
        bitsRemaning--;
        boolean bit = ((byteToRead >> bitsRemaning) & 1) == 1;
        if (bitsRemaning == 0) {
            fillBits();
        }
        return bit;
    }

    /**
     * @return returns if there is anything left to be read from the file
     */
    public boolean isEmpty() {
        return byteToRead == -1;
    }

}
