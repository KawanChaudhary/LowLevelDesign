package TicTacToe;

import TicTacToe.Model.*;

import java.util.*;

public class TicTacToe {
    Board gameBoard;
    Queue<Player> players;

    public TicTacToe(){
        players = new LinkedList<>();
        PlayingPieceO circle = new PlayingPieceO();
        PlayingPieceX cross = new PlayingPieceX();

        // Players Initialization
        Player p1 = new Player("kawan", circle);
        Player p2 = new Player("yawan", cross);

        players.add(p1);
        players.add(p2);
        gameBoard = new Board(3);
    }

    public int[] takeInput(Player player) {
        Scanner inputScanner = new Scanner(System.in);
        int inputRow = -1;
        int inputColumn = -1;

        // Continue asking for input until valid integers are provided
        while (true) {
            try {
                System.out.print("Player: " + player.name + ", enter your [row column] like 0 2: ");
                String s = inputScanner.nextLine();
                String[] values = s.split(" ");

                // Check if the input has exactly two values
                if (values.length != 2) {
                    System.out.println("Invalid input. Please enter exactly two values separated by space.");
                    continue;
                }

                // Convert the string to integers
                inputRow = Integer.parseInt(values[0]);
                inputColumn = Integer.parseInt(values[1]);

                // If both are valid, break the loop
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values.");
            }
        }

        return new int[]{inputRow, inputColumn};
    }

    public String startGame(){

        boolean noWinner = true;

        while(noWinner){
            //take out the player whose turn is and also put the player in the list back
            Player playerTurn = players.peek();

            if(playerTurn == null) return "Tie";

            //get the free space from the gameBoard
            gameBoard.print();
            if(gameBoard.freeSpace == 0){
                noWinner = false;
                continue;
            }

            //read the user input
            int[] inputs = takeInput(playerTurn);
            int row = inputs[0];
            int col = inputs[1];

            // place the piece
            boolean pieceAddedSuccessfully = gameBoard.add(row, col, playerTurn.playingPiece);

            if(!pieceAddedSuccessfully){
                System.out.println("Already occupied. Please enter other values.");
                continue;
            }

            boolean isWinner = checkWinner(row, col, playerTurn.playingPiece);
            if(isWinner){
                gameBoard.print();
                return playerTurn.name;
            }

            players.poll();
            players.add(playerTurn);
        }
        return "tie";
    }

    public boolean checkWinner(int row, int col, PlayingPiece playingPiece){

        if(row == col || row + col == 2){
            boolean ld = true, rd = true;
            for(int i=0; i<3; i++){
                ld = ld && (gameBoard.board[i][i] == playingPiece);
                rd = rd && (gameBoard.board[i][2-i] == playingPiece);
            }
            if(ld || rd) return true;
        }

        boolean rowCheck = true, colCheck = true;

        for(int i=0; i<3; i++){
            rowCheck = rowCheck && (gameBoard.board[i][col] == playingPiece);
            colCheck = colCheck && (gameBoard.board[row][i] == playingPiece);
        }

        return rowCheck || colCheck;
    }

}
