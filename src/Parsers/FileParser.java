package src.Parsers;

import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
    private final String filePath;

    public FileParser(String filePath){
        this.filePath = filePath;
    }

    public Set<String> parse() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Set<String> words = new HashSet<>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    words.add(part.trim());
                }
            }
            return words;
        } catch (IOException e) {
            System.out.println("С файлом что-то не так. Перезапустите программу, убедитесь, что файл существует.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}

