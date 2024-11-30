public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean castling0() {
        int row = nowPlayer.equals("White") ? 0 : 7;
        King king = (King) board[row][4];
        Rook rook = (Rook) board[row][0];

        if (king != null && rook != null &&
                king.isCheck() && rook.isCheck() &&
                board[row][1] == null && board[row][2] == null && board[row][3] == null &&
                !king.isUnderAttack(this, row, 4) &&
                !king.isUnderAttack(this, row, 3) &&
                !king.isUnderAttack(this, row, 2)) {

            board[row][2] = king;
            board[row][3] = rook;
            board[row][4] = null;
            board[row][0] = null;

            king.setCheck(false);
            rook.setCheck(false);

            return true;
        }

        return false;
    }

    public boolean castling7() {
        int row = nowPlayer.equals("White") ? 0 : 7;
        King king = (King) board[row][4];
        Rook rook = (Rook) board[row][7];

        if (king != null && rook != null &&
                king.isCheck() && rook.isCheck() &&
                board[row][5] == null && board[row][6] == null &&
                !king.isUnderAttack(this, row, 4) &&
                !king.isUnderAttack(this, row, 5) &&
                !king.isUnderAttack(this, row, 6)) {

            board[row][6] = king;
            board[row][5] = rook;
            board[row][4] = null;
            board[row][7] = null;

            king.setCheck(false);
            rook.setCheck(false);

            return true;
        }

        return false;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
            ChessPiece movingPiece = board[startLine][startColumn];
            if (movingPiece instanceof King) {
                ((King) movingPiece).setCheck(false);
            } else if (movingPiece instanceof Rook) {
                ((Rook) movingPiece).setCheck(false);
            }
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}