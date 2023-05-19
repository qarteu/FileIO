import java.io.File;

public class BatchProces {
    public static void main(String[] args) {
        File dir = new File(".");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

    }
}
