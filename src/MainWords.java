import javax.swing.table.TableCellEditor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainWords {
    public static HashMap<String, Integer> syllableMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {

        String fileContents = TextLib.readFile("Siddarta.txt");
        String fileContents1 = TextLib.readFile("greeneggsandham.txt");
        String fileContents2 = TextLib.readFile("countofmontecristo.txt");


        // ======= test of splitting into sentences ========
        ArrayList<String> sentences = TextLib.splitIntoSentences(fileContents);
        ArrayList<String> sentences1 = TextLib.splitIntoSentences(fileContents1);
        ArrayList<String> sentences2 = TextLib.splitIntoSentences(fileContents2);

        System.out.println(sentences.size());

        // ============= test of extracting words ===========
        ArrayList<String> words = TextLib.extractedWords(sentences1);

        // ================= test of syllables ==============
        //for (String word : words) {
            //System.out.println(word + " : " + getSyllables(word));


        //}
        // String input = "Are you trying to go to the park";
        //double wordSentenceRatio = (double) TextLib.totalWords(input) / (double) TextLib.totalSentences(sentences1);
        //System.out.println("total word: " + TextLib.totalWords(fileContents));
        //System.out.println("total sentences: " + TextLib.totalSentences(sentences1));
        //double output =  206.835 - 1.015*(wordSentenceRatio) - 84.6(TextLib.getSyllables(fileContents)/(double) TextLib.totalWords(fileContents));

       /* getSyllables(data);
      for (Map.Entry<String, Integer> entry : syllableMap.entrySet()) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }*/
        //System.out.println("MSE: " + testApproximator());
        System.out.println("Readability : "  +  TextLib.readability(fileContents2, sentences2));
        System.out.println("Smog test: " + TextLib.smogTest(words,sentences1));

    }


    public static int getSyllables(String word) throws IOException {
        if (syllableMap.size() < 1) loadSyllableMap("syllables.txt");
        word = word.toLowerCase();

        if (syllableMap.containsKey(word)) {
            return syllableMap.get(word);        // if we know the answer return it
        } else {
            return 2;                    // otherwise assume it’s a 2 syllable word
        }
    }

    private static void loadSyllableMap(String filename) throws IOException {
        String words = readFile(filename);
        String[] lines = words.split("\n");
        for (String line : lines) {
            String[] pairWords = line.split("=");
            String word = pairWords[0];
            String astWord = pairWords[1];
            int numSyllables = 1;
            for (int i = 0; i < astWord.length(); i++) {
                if (astWord.substring(i, i + 1).equals("*")) numSyllables++;
            }


            syllableMap.put(word, numSyllables);
        }
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static double testApproximator() throws IOException {
        if (syllableMap.size() < 1) {
            loadSyllableMap("syllables.txt");
        }
        int countWord = 0;
        double squaredErrorSum = 0;
        for (String line : syllableMap.keySet()) {
            countWord++;
            String[] words = line.split("=");
            String keyWord = words[0];
            //my  syllables code running below
            int prediction = TextLib.getSyllablesStr(keyWord);
            int real = syllableMap.get(keyWord);
            if (prediction != real) {
                System.out.println("Word: " + keyWord + " .....Prediction: " + prediction + ".....Real: " + real);
            }
            double error = Math.abs(prediction - real);
            squaredErrorSum += (error * error);
        }
        double MSE = (squaredErrorSum / countWord);
        return MSE;
    }
}

