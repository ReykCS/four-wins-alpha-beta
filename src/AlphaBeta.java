public class AlphaBeta {
    Board board;
    int maxDepth;

    public AlphaBeta(Board board)   {
        this.board = new Board(board);
        this.maxDepth = 10;
    }

    public int evaluatePossibleMoves(int player)  {
        int moveToDo = 0;
        int moveScore = Integer.MIN_VALUE;
        for ( int move : this.board.getPossibleMoves() ) {
            this.board.doMove(move, player);
            int score = - this.alphaBeta(-player);
            if ( score > moveScore ) {
                moveToDo = move;
                moveScore = score;
            }
            this.board.undoMove(move);
            System.out.println(move + " " + score);
        }
        return moveToDo;
    }

    public int alphaBeta(int player)    {
        return this.pruning(player, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }

    public int pruning(int player, int alpha, int beta, int depth) {
        //System.out.println("Pruning " + depth);
        //this.board.printBoard();
        if (this.board.gameWon() != 0) {
            //System.out.println("SCORE: " + (-1 * depth));
            //this.board.printBoard();
            return -1 * (this.board.getGameState() + 1);
        }
        if ( depth >= this.maxDepth ) {
            return 0;
        }
        if ( this.board.getGameState() <= 0 ) return 0;
        for ( int move : this.board.getPossibleMoves() )    {
            this.board.doMove(move, player);
            int score = -1 * this.pruning(- player, - beta, - alpha, depth + 1);
            this.board.undoMove(move);
            if ( score > alpha )    {
                alpha = score;
                if ( alpha >= beta ) break;
            }
        }
        return alpha;
    }
}
