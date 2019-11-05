import java.util.Scanner;

import static java.lang.System.exit;

public class FourWins {
    public static void main(String[] argv)  {
        System.out.println("Vier gewinnt game");

        Board board = new Board(7, 6);
        boolean player = true;
        board.printBoard();
        while ( board.gameWon() == 0 ) {
            Scanner scan = new Scanner(System.in);
            int move = scan.nextInt();
            if (move < 1 || move > 7) exit(1);
            board.doMove(move, player ? 1 : -1);
            board.printBoard();
            player = ! player;
            /*for ( int i : board.getPossibleMoves()) {
                System.out.print(i + " ");
            }*/
            AlphaBeta ab = new AlphaBeta(board);
            //int score = ab.alphaBeta(player ? 1 : -1);
            int score = ab.evaluatePossibleMoves(player ? 1 : -1);
            System.out.println(board.gameWon() + " " + score);
            board.doMove(score, player ? 1 : -1);
            board.printBoard();
            player = ! player;
            //board.doMove();
        }
        //board.undoMove(move);
        //board.printBoard();
    }
}
