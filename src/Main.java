import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // writeDataToFile("words.txt", "hello");

        String data = readFile("words.txt");
        // System.out.println("File contains: " + data);
        System.out.println("Words Length: " + data.length());
        String fileContents = readFile("words.txt");


        String[] words = fileContents.split("\n");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
        // getWordsWithout(words, "e");
        //System.out.println(filterForPattern(words, "****p**z*"));
        System.out.println(countSyllables(words));

    }


    private static int countSyllables(String[] allWords) {
        int totalSyllables = 0;
        for (String word : allWords) {
            System.out.println(word + " " + vowelConstant(word));

        }
        return totalSyllables;
    }

    private static int vowelConstant(String word) {
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


    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void writeDataToFile(String filePath, String data) {
        try (FileWriter f = new FileWriter(filePath);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {


            writer.println(data);


        } catch (IOException error) {
            System.err.println("There was a problem writing to the file: " + filePath);
            error.printStackTrace();
        }
    }

    public static ArrayList<String> getNLetterWords(String[] allWords, int n) {
        int count = 0;
        ArrayList<String> sameNumWords = new ArrayList<String>();
        for (String word : allWords) {
            if (word.length() == n) {
                sameNumWords.add(word);
                count++;
            }
        }
        System.out.println("# of letters: " + n + " Amount: " + count);
        return sameNumWords;

    }

    public static ArrayList<String> getWordsWithout(String[] allWords, String forbiddenLetter) {
        int count = 0;
        ArrayList<String> wordsWithout = new ArrayList<String>();
        for (String word : allWords) {
            if (!word.contains(forbiddenLetter)) {
                wordsWithout.add(word);
                count++;
            }
        }
        System.out.println("count: " + count);
        return wordsWithout;
    }


    public static ArrayList<String> filterForPattern(String[] words, String pattern) {
        int notMatch = 0;
        ArrayList<String> samePattern = new ArrayList<String>();
        for (String word : words) {
            if (isWordPattern(pattern, word)) samePattern.add(word);

        }
        return samePattern;
    }


    private static boolean isWordPattern(String pattern, String word) {
        if (word.length() == pattern.length()) {
            for (int j = 0; j < pattern.length(); j++) {
                if (!(pattern.substring(j, j + 1).equals("*")) && !(pattern.substring(j, j + 1).equals(word.substring(j, j + 1)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static ArrayList<String> patternFound(String word, String pattern, int notMatch) {
        ArrayList<String> samePattern = new ArrayList<String>();
        notMatch = 0;
        for (int j = 0; j < pattern.length(); j++) {
            if (!(pattern.substring(j, j + 1).equals("*")) && !(pattern.substring(j, j + 1).equals(word.substring(j, j + 1)))) {
                notMatch++;
            }
        }
        if (notMatch == 0) samePattern.add(word);
        return samePattern;
    }
}
