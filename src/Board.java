import java.util.ArrayList;
import java.util.Collections;

public class Board {
    int width;
    int height;
    int[][] field;
    public Board(int width, int height)  {
        this.width = width;
        this.height = height;
        this.field = new int[height][width];
    }

    public Board(Board b)   {
        this.width = b.width;
        this.height = b.height;
        this.field = new int[this.height][this.width];
        for ( int i = 0; i < this.height; i++)  {
            for ( int j = 0; j < this.width; j++)   {
                this.field[i][j] = b.field[i][j];
            }
        }
    }

    public void doMove(int col, int player) {
        col--;
        for ( int i = this.height - 1; i >= 0; i--)  {
            if ( this.field[i][col] == 0 ) {
                this.field[i][col] = player;
                return;
            }
        }
        System.out.println("No move possible!");
    }

    public void undoMove(int col)   {
        col--;
        for ( int i = 0; i < this.height; i++)  {
            if ( this.field[i][col] != 0 ) {
                this.field[i][col] = 0;
                return;
            }
        }
    }

    public int getGameState()   {
        int state = 0;
        for ( int i = 0; i < this.height; i++)  {
            for ( int j = 0; j < this.width; j++)   {
                if ( this.field[i][j] == 0 ) state++;
            }
        }
        return state;
    }

    public int gameWon()    {
        for ( int i = 0; i < this.height; i++)   {
            for ( int j = 0; j < this.width; j++)  {
                if ( this.field[i][j] == 0 ) continue;

                int player = this.field[i][j];
                int counter = 1;
                if ( j <= this.width - 4)   {
                    for ( int x = j + 1; x < this.width; x++)   {
                        if ( this.field[i][x] == player ) counter++;
                        else break;
                    }
                    if ( counter == 4 ) return player;
                }
                counter = 1;
                if ( i <= this.height - 4)  {
                    for ( int y = i + 1; y < this.height; y++)  {
                        if ( this.field[y][j] == player ) counter++;
                        else break;
                    }
                    if ( counter == 4 ) return player;
                }
                counter = 1;
                if ( (i <= this.height - 4) && (j <= this.width - 4) ) {
                    for (int x = j + 1, y = i + 1; x < this.width && y < this.height; x++, y++) {
                        if (this.field[y][x] == player) counter++;
                        else break;
                    }
                    if ( counter == 4 ) return player;
                }
                counter = 1;
                if ( (i <= this.height - 4) && (j >= 3) )   {
                    for( int x = j - 1, y = i + 1; x >= 0 && y < this.height; x--, y++) {
                        if ( this.field[y][x] == player ) counter++;
                        else break;
                    }
                    if ( counter == 4 ) return player;
                }
            }
        }
        return 0;
    }

    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for ( int i = 0; i < this.width; i++)   {
            if ( this.field[0][i] == 0 ) moves.add(i + 1);
        }
        Collections.shuffle(moves);
        return moves;
    }

    public void printBoard()    {
        System.out.println("\nBoard:");
        System.out.print(" | ");
        for ( int j = 0; j < this.width; j++ )  {
            System.out.print((j + 1) + " | ");
        }
        System.out.println();
        System.out.println();

        for ( int i = 0; i < this.height; i++)   {
            System.out.print(" | ");
            for ( int j = 0; j < this.width; j++ )  {
                String player = this.field[i][j] == 0 ? "-" : (this.field[i][j] == 1 ? "X" : "O");
                System.out.print(player);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
