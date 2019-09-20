package tests.matricesTest;

import org.junit.Before;
import tests.mockObjects.matrices.mockMatrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    mockMatrix<Integer> mockM;
    Number m[][];
    Number temp[][];

    @Before
    public void setUp(){
        mockM = new mockMatrix<>(3, 3);
        this.m = mockM.m;
        temp = new Number[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                temp[i][j] = i*j;
            }


    }

    @Test
    public void addValueTest() {
        System.out.println(mockM.m);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                mockM.addValue(i, j, i*j);
            }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                assertEquals(temp[i][j], mockM.m[i][j]);
            }
        }
    }

    @Test
    public void getMatrixTest() {
        mockM = new mockMatrix<>(1,1);
        mockM.addValue(0,0, 3);
        assertEquals(mockM.m, this.mockM.getMatrix());
    }

    @Test
    public void getTest() {
        assertEquals(9.0, mockM.get(2,2));
    }

    @Test
    public void checkIfSquareTest() {
        assertFalse(mockM.checkIfSquare());
    }

    @Test
    public void toStringTest() {
        assertTrue("Some Str".equals(mockM.toString()));
        assertFalse("SomeStr".equals(mockM.toString()));
    }

    @Test
    public void initialiseTest() {
        mockM.initialise();
        assertEquals(null,mockM.m);
    }

    @Test
    public void iterTest(){
        mockM = new mockMatrix<>(3, 3);
        int iter = 3;
        for(Number num : mockM){
            iter --;
            assertTrue(iter == num.intValue());
        }

    }
}