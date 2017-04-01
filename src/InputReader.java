import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinan on 3/28/17.
 */
public class InputReader {
    BufferedReader br;
    List<String> list = new ArrayList<>();

    public InputReader(String filePath) {
        File file = new File(filePath);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(reader);
    }

    public String[] parseInput() {
        String line = null;
        try {
            line = br.readLine();
            while (line != null) {
                if (!line.equals("\r\n")) {
                    list.add(line);
                    line = br.readLine();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return strings;
    }
}
