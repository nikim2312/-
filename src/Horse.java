public class Horse extends ChessPiece {
    public Horse(String color)
    {
        super(color);

    }
    @Override
    public String getColor()
    {
        return this.color;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard,
                                              int line, int column,
                                              int toLine, int toColumn)
    {
        if (toLine == line && toColumn == column)
        {
            return false;
        }
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn))
        {
            return false;
        }

        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece == null || !targetPiece.getColor().equals(this.color);
        }
        return false;
    }
}
