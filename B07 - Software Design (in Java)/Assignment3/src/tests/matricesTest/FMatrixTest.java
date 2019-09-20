package tests.matricesTest;

import tests.mockObjects.matrices.mockFMatrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FMatrixTest {
    mockFMatrix<Integer> mockM;
    @Before
    public void setUp() throws Exception {



    }

    @Test
    public void constructFMatrixTest() {
        mockM = mockFMatrix.constructFileMatrix("fileName");
        assertTrue((null != mockM.m));
    }

    @Test
    public void addByFileTest() {
        mockM = mockFMatrix.constructFileMatrix("");
        mockM.addByFile();
        assertTrue(42 == mockM.m[0][0].intValue());
    }
}