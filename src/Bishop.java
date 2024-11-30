public class Bishop extends ChessPiece
{
    public Bishop(String color)
    {
        super(color);

    }

    @Override
    public String getColor()
    {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }


        if (line == toLine && column == toColumn) {
            return false;
        }


        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) {
            return false;
        }


        int stepLine = (toLine - line) > 0 ? 1 : -1;
        int stepColumn = (toColumn - column) > 0 ? 1 : -1;

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false; //
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }


        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);

    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
