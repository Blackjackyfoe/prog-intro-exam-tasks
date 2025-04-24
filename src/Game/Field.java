package src.Game;

public class Field {
    private final int width;
    private final int height;
    private char[][] array;

    public Field(int width, int height){
        this.width = width;
        this.height = height;
        this.array = new char[width][height];
    }

    public char[][] getBoard() {
        return array;
    }

    public void setBoard(char[][] board) {
        this.array = board;
    }


}
