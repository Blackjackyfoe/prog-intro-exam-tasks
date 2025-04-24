package src;

import src.Parsers.FileParser;
import src.Game.Game;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3){
            System.out.println("Неверное число аргументов! Перезапустите программу.");
            System.exit(0);
        }

        String filePath = args[0];
        FileParser fileParser = new FileParser(filePath);
        Set<String> dict = fileParser.parse();
        int m = 0, n = 0;
        try{
            m = Integer.parseInt(args[1]);
            n = Integer.parseInt(args[2]);
            if (m <= 0 || n <= 0 || m > 10 || n > 10){
                System.out.println("Неправильный ввод параметров. M и n должны быть меньше 10, но больше 0");
                System.exit(0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("M и n не являются целыми числами. Перезапумтите программу.");
            System.exit(0);
        }
        Game.play(dict, m, n);

    }
}