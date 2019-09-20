package tests.matricesTest;

import tests.mockObjects.matrices.mockSquareMatrix;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquareMatrixTest {



    @Test
    public void takeDiffNSquare() {
        mockSquareMatrix m = new mockSquareMatrix<Integer>();
        Integer[] temp = {3,1,4,1,5};
        assertTrue(((float)3.1415) == m.takeDiffNSquare(temp, temp));
    }

    @Test
    public void addByMatrixMultiply() {
        mockSquareMatrix mat = new mockSquareMatrix<Integer>();
        Integer[][] temp = new Integer[1][1];
        mat.addByMatrixMultiply(temp);
        assertTrue(temp == mat.m);
    }
}