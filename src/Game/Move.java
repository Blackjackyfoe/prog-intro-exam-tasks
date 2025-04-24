package src.Game;


public class Move {
    private int row;
    private int col;
    private char letter;
    private String word;

    public Move(int row, int col, char letter, String word) {
        this.row = row;
        this.col = col;
        this.letter = letter;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getLetter() {
        return letter;
    }


}