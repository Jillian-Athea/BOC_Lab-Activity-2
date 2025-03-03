import java.io.*;
import java.util.*;

public class RecursiveFileSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter directory path: ");
        String directoryPath = scanner.nextLine();
        
        System.out.print("Enter file extension to search for: ");
        String extension = scanner.nextLine();
        scanner.close();
        
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Invalid directory path!");
            return;
        }
        
        try (FileWriter writer = new FileWriter("search_results.txt")) {
            searchFiles(dir, extension, writer);
            System.out.println("Search completed. Results saved to search_results.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchFiles(File dir, String extension, FileWriter writer) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;
        
        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(file, extension, writer);
            } else if (file.getName().endsWith(extension)) {
                System.out.println("File found: " + file.getAbsolutePath());
                writer.write("File found: " + file.getAbsolutePath() + "\n");
            }
        }
    }
}
