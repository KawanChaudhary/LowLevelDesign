package TicTacToe.Model;

public class Board {
    public int size;
    public PlayingPiece[][] board;
    public int freeSpace;

    public Board(int size){
        this.size = size;
        this.board = new PlayingPiece[size][size];
        this.freeSpace = size*size;
    }

    public boolean add(int row, int col, PlayingPiece playingPiece){
        if(board[row][col] != null) return false;
        this.board[row][col] = playingPiece;
        freeSpace--;
        return true;
    }

    public void print(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j] != null){
                    System.out.print(board[i][j].PieceType.name() + " ");
                }
                else{
                    System.out.print("- ");
                }
                System.out.print("| ");
            }
            System.out.println();
        }
    }

}
