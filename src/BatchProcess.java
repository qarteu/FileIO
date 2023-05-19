import javax.swing.table.TableCellEditor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchProcess {

    public static void main(String[] args) {
        HashMap<String, Double> data = getReadabilityScores("InputText/");
        saveToFile(data);

    }

    private static void saveToFile(HashMap<String, Double> output) {
        try(FileWriter f = new FileWriter("results.txt");
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b)
        ){
            for (String word : output.keySet()) {
                writer.println(word + " " + output.get(word));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static HashMap<String, Double> getReadabilityScores(String path) {
        HashMap<String, Double> output = new HashMap<>();
        File dir = new File(path);
        File[] filesList = dir.listFiles();

        for (File file : filesList) {
            String name = file.getName();
            try {
                String contents = TextLib.readFile(file.getAbsolutePath());
                double score = TextLib.readability(contents, TextLib.splitIntoSentences(contents));
                output.put(name, score);
            } catch (Exception errorObj) {
                System.out.println("An error occurred with file:" + name);
                errorObj.printStackTrace();
            }
        }
        return output;
    }
}

