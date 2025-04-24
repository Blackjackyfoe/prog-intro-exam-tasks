package src.Parsers;

import src.Game.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BestMoveParser {

    public static Move findBestMove(char[][] board, Set<String> dict) {
        int m = board.length;
        int n = board[0].length;

        Move bestMove = null;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == '.') {
                    for (char letter = '\u0430'; letter <= '\u044F'; letter++) {
                        bestMove = tryLetter(board, row, col, letter, dict, bestMove);
                    }
                }
            }
        }

        return bestMove;
    }

    private static Move tryLetter(char[][] board,
                                  int row, int col,
                                  char letter,
                                  Set<String> dict,
                                  Move bestMove) {
        board[row][col] = letter;
        List<String> foundWords = findWordsIncludingCell(board, row, col, dict);

        for (String w : foundWords) {
            if (bestMove == null || w.length() > bestMove.getWord().length()) {
                bestMove = new Move(row, col, letter, w);
            }
        }

        board[row][col] = '.';
        return bestMove;
    }

    private static List<String> findWordsIncludingCell(char[][] board,
                                                       int startRow, int startCol,
                                                       Set<String> dictionary) {
        List<String> result = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        dfs(board, startRow, startCol,
                String.valueOf(board[startRow][startCol]),
                visited, dictionary, result);

        return result;
    }

    private static void dfs(char[][] board,
                            int row, int col,
                            String wordSoFar,
                            boolean[][] visited,
                            Set<String> dictionary,
                            List<String> result) {
        visited[row][col] = true;

        if (dictionary.contains(wordSoFar) && !result.contains(wordSoFar)) {
            result.add(wordSoFar);
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nr = row + dRow[i];
            int nc = col + dCol[i];
            if (nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) {
                continue;
            }
            if (visited[nr][nc]) {
                continue;
            }

            char c = board[nr][nc];
            if (c == '.') {
                continue;
            }
            dfs(board, nr, nc, wordSoFar + c, visited, dictionary, result);
        }

        visited[row][col] = false;
    }
}
