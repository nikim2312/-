public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (!board.checkPos(line) || !board.checkPos(column) || !board.checkPos(toLine) || !board.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }


        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            return isPathClear(board, line, column, toLine, toColumn);
        }


        if (line == toLine || column == toColumn) {
            return isPathClear(board, line, column, toLine, toColumn);
        }

        return false;
    }


    private boolean isPathClear(ChessBoard board, int line, int column, int toLine, int toColumn) {
        int dLine = Integer.signum(toLine - line);
        int dColumn = Integer.signum(toColumn - column);

        int currentLine = line + dLine;
        int currentColumn = column + dColumn;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (board.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += dLine;
            currentColumn += dColumn;
        }

        return board.board[toLine][toColumn] == null ||
                !board.board[toLine][toColumn].getColor().equals(this.color);
    }
}
