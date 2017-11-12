package nl.infosupport.moj.candycrush;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CandyCrushPuzzleTest {

    private CandyCrushPuzzle candyCrushPuzzle = new CandyCrushPuzzle();

    @Test
    public void test1() {
        char[][] board = new char[][]{
                {'R', 'Y', 'G', 'Y'},
                {'Y', 'P', 'G', 'R'},
                {'P', 'O', 'O', 'Y'},
                {'B', 'O', 'B', 'O'}};

        boolean movable = candyCrushPuzzle.hasMovableFields(board);

        assertThat(movable, is(true));
    }

    @Test
    public void test2() {
        char[][] board = new char[][]{
                {'R', 'B', 'G', 'Y'},
                {'P', 'O', 'R', 'Y'},
                {'G', 'Y', 'P', 'O'},
                {'R', 'B', 'G', 'Y'}};

        boolean movable = candyCrushPuzzle.hasMovableFields(board);

        assertThat(movable, is(true));
    }

    @Test
    public void test3() {
        char[][] board = new char[][]{
                {'R', 'B', 'G', 'Y'},
                {'P', 'O', 'R', 'B'},
                {'G', 'Y', 'P', 'O'},
                {'R', 'B', 'G', 'Y'}};

        boolean movable = candyCrushPuzzle.hasMovableFields(board);

        assertThat("Looks tough!", movable, is(false));
    }

    @Test
    public void test4() {
        char[][] board = new char[][]{
                {'Y', 'Y', 'R', 'Y'},
                {'Y', 'R', 'Y', 'Y'}};

        boolean movable = candyCrushPuzzle.hasMovableFields(board);

        assertThat("This board looks weird.,,",
                movable, is(false));
    }
}
