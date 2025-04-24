package src.Game;

import java.util.Scanner;
import java.util.Set;

import static src.Parsers.BestMoveParser.findBestMove;

public class Game {

    public static void play(Set<String> dict, int n, int m){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Отлично, вы ввели всё правильно, запускаю игру...");
        while (true) {
            Field board = new Field(m, n);
            boolean stopRequested = false;
            char[][] tempBoard = new char[m][n];
            System.out.println("Вводите символы. '.' считается за пустой символ.");
            for (int i = 0; i < m; i++) {
                System.out.printf("Строка %d/%d: ", (i+1), m);
                if (!scanner.hasNextLine()) {
                    System.out.println("Неправильный ввод строк");
                    i--;
                    continue;
                }
                String line = scanner.nextLine().trim().toLowerCase();
                if (line.equals("stop")) {
                    stopRequested = true;
                    break;
                }
                if (line.length() != n) {
                    System.err.println("Ошибка! Должно быть ровно " + n + " символов в строке.");
                    i--;
                    continue;
                }
                tempBoard[i] = line.toCharArray();
            }
            board.setBoard(tempBoard);

            if (stopRequested) {
                System.out.println("Выход из программы по запросу пользователя.");
                break;
            }
            System.out.println("Поиск лучшего ходаа...");
            Move bestMove = findBestMove(board.getBoard(), dict);
            if (bestMove == null) {
                System.out.println("Не удалось найти подходящее слово.");
            } else {
                System.out.println("Лучший ход:");
                System.out.println("  Координаты (строка, столбец): (" + bestMove.getRow() + ", " + bestMove.getCol() + ")");
                System.out.println("  Буква: " + bestMove.getLetter());
                System.out.println("  Полученное слово: " + bestMove.getWord());
            }

            System.out.println("\nДля ввода нового положения введите заново m строк или 'stop' для выхода.");
        }
        scanner.close();
    }
}
