
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class FileIO {
    /*public static void main(String[] args) {
        ArrayList<Word> words = readSyllablesFile("data/syllables.txt");
        for (Word w : words){
            System.out.println(w.getWord() + " : " + w.getSyllables());
        }
        String result  = readFileAsString("data/syllables.txt");
        System.out.println(result.substring(0,100));
    }

    public static ArrayList<Word> readSyllablesFile(String filename){
        Scanner scanner;
        ArrayList<Word> words = new ArrayList<Word>();

        try{
            scanner = new Scanner(new FileReader(filename));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                //---process line here
                String word = getWordFromLine(line);
                int syllables = getSyllablesFromLine(line);

                //----
                Word w = new Word(word,syllables);
                words.add(w);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return words;
    }



    private static String readFileAsString(String filename) {
        Scanner scanner;
        String output = "";

        try{
            scanner = new Scanner(new FileReader(filename));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                output+= line.trim();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + filename);
        }
        return output;
    }*/

}
