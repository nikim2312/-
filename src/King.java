public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (!board.checkPos(line) || !board.checkPos(column) || !board.checkPos(toLine) || !board.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);

        if (deltaLine <= 1 && deltaColumn <= 1) {
            return board.board[toLine][toColumn] == null ||
                    !board.board[toLine][toColumn].getColor().equals(this.color);
        }

        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (!board.checkPos(line) || !board.checkPos(column)) {
            return false;
        }

        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board[i].length; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public boolean isCheck() {
        return check;
    }

}
