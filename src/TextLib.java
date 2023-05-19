import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ArrayList;
public class TextLib {

    public static ArrayList<String> splitIntoSentences(String text) {
        text = text.replaceAll("[\\s]+", " "); // collapse whitespace

        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while (boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length() > 0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length() > 0)
            output.add(sentence);

        return output;
    }

    public static int totalSentences(ArrayList output) {
        return output.size();
    }


    public static ArrayList<String> splitIntoSentences2(String text) {
        text = text.replaceAll("[\\s]+", " "); // collapse whitespace

        // remove punctuation
        String noPunctuationText = text.replaceAll("[^a-zA-Z -]", "");

        // splits when it sees a . ! or ? followed by whitespace
        String[] sentences = text.split("[.!?]+\\s+");

        // convert array into ArrayList
        return new ArrayList<String>(Arrays.asList(sentences));
    }

    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath));) {

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }

        } catch (Exception errorObj) {
            System.err.println("There was a problem reading the " + filePath);
        }

        return sb.toString();
    }

    public static void writeToFile(String filePath, String data) {
        try (FileWriter f = new FileWriter(filePath);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {

            writer.print(data);

        } catch (Exception errorObj) {
            System.out.println("There was an error with the file");
            errorObj.printStackTrace();
        }
    }

    public static String cleanWord(String word) {
        String returnString = "";
        for (int i = 0; i < word.length(); i++) {
            if (isLetter(word.substring(i, i + 1))) {
                returnString = returnString + word.substring(i, i + 1);
            }
        }
        return returnString;
    }

    private static boolean isLetter(String letter) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        if (letter.length() != 1) return false;
        return alph.contains(letter.toLowerCase());


    }

    public static int getSyllablesStr(String word) {
        int countOfVC = 0;
        String modWord = 'x' + word + 'x';
        for (int i = 1; i < modWord.length(); i++) {
            if (isVowel(modWord.substring(i, i + 1)) && isConsonant(modWord.substring(i - 1, i))) {
                countOfVC++;
            }

        }
        //if ( isConsonant(word.substring(word.length()-2,word.length()-1))&& word.endsWith("y")) countOfVC++;
        if (word.substring(word.length() - 1).equals("ed")) countOfVC--;

        return countOfVC;
    }
    public static int getSyllables1(ArrayList<String> text) {
        int countOfVC = 0;
        for (int i = 0; i < text.size(); i++) {
            String word = text.get(i);
            String modWord = 'x' + word + 'x';
            for (int j = 1; j < modWord.length(); j++) {
                if (isVowel(modWord.substring(j, j + 1)) && isConsonant(modWord.substring(j - 1, j))) {
                    countOfVC++;
                }
            }
            //if ( isConsonant(word.substring(word.length()-2,word.length()-1))&& word.endsWith("y")) countOfVC++;
           if (word.substring(word.length() - 1).equals("ed")) countOfVC--;
        }
        return countOfVC;
    }

    public static boolean isConsonant(String letter) {
        String consonants = "bcdfghjklmnpqrstvwxyz";
        if (letter.length() != 1) return false;
        return consonants.contains(letter.toLowerCase());

    }

    public static boolean isVowel(String letter) {
        // not including y and keeping common vowels (sometimes y can be a vowel)
        String vowels = "aeiouy";
        if (letter.length() != 1) return false;
        return vowels.contains(letter.toLowerCase());

    }


    public static ArrayList<String> extractedWords(ArrayList<String> arrayList) {
        ArrayList<String> extractedWords = new ArrayList<String>();
        for (String word : arrayList) {
            String[] words = word.split(" ");
            for (int i = 0; i < words.length; i++) {
                if(!words[i].equals("")) extractedWords.add(words[i]);
            }
        }
        return extractedWords;
    }

    public static int totalWordsArr(ArrayList<String> arrayList) {
        int wc = 0;
        for (int j = 0; j < arrayList.size(); j++) {
            String str = arrayList.get(j);
            boolean checkSpace = true;
            int i = 0;
            while (i < str.length()) {
                if (str.substring(i, i + 1).equals(" ") || str.substring(i, i + 1).equals("\n") || str.substring(i, i + 1).equals("\t"))
                    checkSpace = true;

                else if (checkSpace) {
                    checkSpace = false;
                    wc++;
                }
                i++;
            }


        }
        return wc;
    }
   public static double readability(String fileContents, ArrayList sentences) {
        double totalWords = totalWordsStr(fileContents);
        double totalSen = sentences.size();
        double totalSyl = getSyllables1(sentences);
        return (206.835 - 1.015 *  (totalWords / totalSen) - 84.6 * (totalSyl / totalWords));

    }
    public static double smogTest(ArrayList<String> text,ArrayList<String> sentences) {
        double totalSen = sentences.size();
        if(totalSen<30)return -1;
        double totalSyl = 0;
         for(String word : text)
            if(getSyllablesStr(word)>= 3){
                 totalSyl++;
        }

        double smogScore = (Math.sqrt(totalSyl * 30 / totalSen) * 1.0430 + 3.1291);
        return smogScore;
    }



    public static int totalWordsStr(String str) {
        boolean checkSpace = true;
        int i = 0;
        int wc = 0;
        while (i < str.length()) {
            if (str.substring(i, i + 1).equals(" ") || str.substring(i, i + 1).equals("\n") || str.substring(i, i + 1).equals("\t"))
                checkSpace = true;

            else if (checkSpace) {
                checkSpace = false;
                wc++;
            }
            i++;

        }
        return wc;
    }
}