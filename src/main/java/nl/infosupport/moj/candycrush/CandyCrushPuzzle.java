package nl.infosupport.moj.candycrush;

public class CandyCrushPuzzle {

    private char[][] board = new char[][]{
            {'R', 'Y', 'G', 'Y'},
            {'Y', 'P', 'G', 'R'},
            {'P', 'O', 'O', 'Y'},
            {'B', 'O', 'B', 'O'}};

    public boolean hasMovableFields(char[][] board) {
        if (!this.isValidBoardStructure(board))
            return false;

        this.board = board;

        // Horizontal check
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            boolean isSolvable = this.isSolvableWithRow(rowIndex, board[rowIndex]);
            if (isSolvable) {
                return true;
            }
        }

        // Vertical check
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {

            char[] row = new char[board[rowIndex].length];
            for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++) {
                row[columnIndex] = board[columnIndex][rowIndex];
            }

            boolean isSolvable = this.isSolvableWithRow(rowIndex, row);
            if (isSolvable) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidBoardStructure(char[][] board) {
        if (board.length < 3 || board.length > 100)
            return false;

        for (char[] row : board) {
            if (row.length < 3 || row.length > 100)
                return false;
        }

        return true;
    }

    private boolean isSolvableWithRow(int rowIndex, char[] row) {
        int firstConsecutiveColorIndex = this.findFirstConsecutiveColorIndex(row);
        if (firstConsecutiveColorIndex == -1) // no consecutive colors found
            return false;

        if (this.isSolvableWithinSameRow(row, firstConsecutiveColorIndex))
            return true;

        if (this.isSolvableWithOuterRow(rowIndex, row, firstConsecutiveColorIndex))
            return true;

        return false;
    }

    private int findFirstConsecutiveColorIndex(char[] row) {
        int firstConsecutiveColorIndex = -1;
        for (int index = 0; index < row.length; index++) {
            char charCurrent = row[index];

            if (index != 0 && charCurrent == row[index - 1]) { // char before current char
                firstConsecutiveColorIndex = index;
                break;
            }

            if (index != row.length - 1 && charCurrent == row[index + 1]) { // char after current char
                firstConsecutiveColorIndex = index;
                break;
            }
        }

        return firstConsecutiveColorIndex;
    }

    private boolean isSolvableWithinSameRow(char[] row, int firstConsecutiveColorIndex) {
        char charTwoPlacesBack = '\0';
        char charTwoPlacesForward  ='\0';

        if (firstConsecutiveColorIndex != 0 && firstConsecutiveColorIndex >= 2) {
            charTwoPlacesBack = row[firstConsecutiveColorIndex - 2];
        }

        if (firstConsecutiveColorIndex != row.length - 1 && firstConsecutiveColorIndex <= row.length - 4) {
            charTwoPlacesForward = row[firstConsecutiveColorIndex + 3];
        }

        if (row[firstConsecutiveColorIndex] == charTwoPlacesBack || row[firstConsecutiveColorIndex] == charTwoPlacesForward) {
            return true;
        }

        return false;
    }

    private boolean isSolvableWithOuterRow(int rowIndex, char[] row, int firstConsecutiveColorIndex) {
        if (rowIndex != 0) { // check row above
            if (row[firstConsecutiveColorIndex] != 0) {
                char charRowAboveOneToTheLeft = this.board[rowIndex - 1][firstConsecutiveColorIndex - 1];
                if (row[firstConsecutiveColorIndex] == charRowAboveOneToTheLeft)
                    return true;
            }

            if (row[firstConsecutiveColorIndex] != row.length - 1) {
                char charRowAboveTwoToTheRight = this.board[rowIndex - 1][firstConsecutiveColorIndex + 1];
                if (row[firstConsecutiveColorIndex] == charRowAboveTwoToTheRight)
                    return true;
            }
        }

        if (rowIndex != this.board.length - 1) { // check row beneath
            if (row[firstConsecutiveColorIndex] != 0) {
                char charRowBelowOneToTheLeft = this.board[rowIndex + 1][firstConsecutiveColorIndex - 1];
                if (row[firstConsecutiveColorIndex] == charRowBelowOneToTheLeft)
                    return true;
            }

            if (row[firstConsecutiveColorIndex] != row.length - 1) {
                char charRowBelowTwoToTheRight = this.board[rowIndex + 1][firstConsecutiveColorIndex + 1];
                if (row[firstConsecutiveColorIndex] == charRowBelowTwoToTheRight)
                    return true;
            }
        }

        return false;
    }
}