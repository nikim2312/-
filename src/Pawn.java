public class Pawn extends ChessPiece
{
    public Pawn(String color)
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


        int direction = this.color.equals("White") ? 1 : -1;


        if (toColumn == column && toLine == line + direction && chessBoard.board[toLine][toColumn] == null) {
            return true;
        }


        if ((this.color.equals("White") && line == 1) || (this.color.equals("Black") && line == 6)) {
            if (toColumn == column && toLine == line + 2 * direction && chessBoard.board[toLine][toColumn] == null &&
                    chessBoard.board[line + direction][column] == null) {
                return true;
            }
        }


        if (Math.abs(toColumn - column) == 1 && toLine == line + direction && chessBoard.board[toLine][toColumn] != null) {
            return !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
        }


        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
