import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;
        do {
            clearBoard();
            String currentPlayer = "X"; // X always moves first
            boolean gameWon = false;
            boolean gameTied = false;

            while (!gameWon && !gameTied) {
                display();
                int rowMove = SafeInput.getRangedInt(console, "Enter the row for your move", 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(console, "Enter the column for your move", 1, 3) - 1;

                if (isValidMove(rowMove, colMove)) {
                    board[rowMove][colMove] = currentPlayer;
                    gameWon = isWin(currentPlayer);
                    gameTied = isTie();
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }

            display();
            if (gameWon) {
                System.out.println("Player " + (currentPlayer.equals("X") ? "O" : "X") + " wins!");
            } else if (gameTied) {
                System.out.println("It's a tie!");
            }

            playAgain = SafeInput.getYNConfirm(console, "Do you want to play again?");
        } while (playAgain);
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < ROW - 1) System.out.println("---------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
