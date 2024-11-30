public class Rook extends ChessPiece
{
    public Rook(String color)
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


        if (line != toLine && column != toColumn) {
            return false;
        }


        if (line == toLine) {
            int step = (toColumn > column) ? 1 : -1;
            for (int currentColumn = column + step; currentColumn != toColumn; currentColumn += step) {
                if (chessBoard.board[line][currentColumn] != null) {
                    return false;
                }
            }
        } else {
            int step = (toLine > line) ? 1 : -1;
            for (int currentLine = line + step; currentLine != toLine; currentLine += step) {
                if (chessBoard.board[currentLine][column] != null) {
                    return false;
                }
            }
        }


        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.color);

    }

    @Override
    public String getSymbol() {
        return "R";
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public boolean isCheck() {
        return check;
    }

}
