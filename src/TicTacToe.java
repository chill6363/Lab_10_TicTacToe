import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        SafeInput.prettyHeader("Tic Tac Toe!");
        //variable setup
        int turnCounter = 1;
        boolean playAgain, isWinCondition = false, isTieCondition = false, player1Turn = true;
        Scanner in = new Scanner(System.in);
        String player1, player2;

        //input player names before game loop
        player1 = SafeInput.getNonZeroLenString(in, "Please input Player 1's name");
        player2 = SafeInput.getNonZeroLenString(in, "Please input Player 2's name");

        //TicTacToe happens entirely in HERE!!
        do{
            clearBoard();
            System.out.println(player1 + " is X's. " + player2 + " is O's. \nGame begins now!");
            //Each individual game is looped here until a win condition is met
            while(!isWinCondition){
                //Player 1's Turn Loop
                if (player1Turn){
                    System.out.println("\nTurn " + turnCounter);
                    display();
                    getPlayerMove(in, player1, player1Turn);
                    isWinCondition = isWin("X");
                    if(isWinCondition){
                        System.out.println(player1 + " is the winner!");
                    }
                    isTieCondition = isTie();
                    player1Turn = false;
                }
                //Player 2's Turn Loop
                else if(!player1Turn && (!isWinCondition || !isTieCondition)){
                    display();
                    getPlayerMove(in, player2, player1Turn);
                    isWinCondition = isWin("O");
                    if(isWinCondition){
                        System.out.println(player2 + " is the winner!");
                    }
                    isTieCondition = isTie();
                    player1Turn = true;
                }
                if(isTieCondition){
                    System.out.println("There are no more valid moves. Its a tie!");
                    isWinCondition = true;
                }
                turnCounter++;
            }
            //Game is over, bool for second loop is reset and players are asked if they'd like to play again
            isWinCondition = false;
            player1Turn = true;
            turnCounter = 1;
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again? (Y/N)");
        }while(playAgain);
    }

    //sets all the board elements to a space
    private static void clearBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                board[i][j] = "_";
            }
        }
    }

    //displays the board, shown before each turn is taken for each player
    private static void display(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //get input from player
    private static void getPlayerMove(Scanner pipe, String player, boolean player1Turn){
        int row, col;
        boolean isValid = false;
        do{
            row = SafeInput.getRangedInt(pipe, "Input " + player + "'s move (row) ", 1, ROWS);
            col = SafeInput.getRangedInt(pipe, "Input " + player + "'s move (col) ", 1, COLS);
            row -= 1;
            col -= 1;
            isValid = isValidMove(row, col);
            if(!isValid){
                System.out.println("Invalid move. Please input a valid move.");
            }
        }while(!isValid);
        if(player1Turn){
            board[row][col] = "X";
        }
        else if(!player1Turn){
            board[row][col] = "O";
        }
    }

    //checks if a move is valid (space is empty), returns true if legal, false if not legal
    private static boolean isValidMove(int row, int col){
        if(board[row][col].equals("X") || board[row][col].equals("O")){
            return false;
        }
        else{
            return true;
        }
    }

    //checks if there is a win condition on the board after each turn is played. Calls the following 3 methods to check for differing win conditions
    private static boolean isWin(String player){
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }
    //checks for a column win condition
    private static boolean isColWin(String player){
        int inARow = 0;
        boolean winner = false;
        for (int i = 0; i < COLS; i++){
            for (int j = 0; j < ROWS; j++){
                if(board[j][i].equals(player)){
                    inARow++;
                }
                if(inARow == 3){
                    winner = true;
                }
            }
            inARow = 0;
        }
        return winner;
    }
    //checks for a row win condition
    private static boolean isRowWin(String player){
        int inARow = 0;
        boolean winner = false;
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                if(board[i][j].equals(player)){
                    inARow++;
                }
                if(inARow == 3){
                    winner = true;
                }
            }
            inARow = 0;
        }
        return winner;
    }
    //checks for a diagonal win condition
    private static boolean isDiagonalWin(String player){
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    //checks for tie condition
    private static boolean isTie(){
        int moveCounter = 0;
            for(int i = 0; i < ROWS; i++){
                for(int j = 0; j < COLS; j++){
                    if(!board[i][j].equals("_")){
                        moveCounter++;
                    }
                }
            }
        return moveCounter == 9;
    }
}
