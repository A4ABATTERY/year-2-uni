package Matrices;

/**
 * This is the SquareMatrix. It is setup by using Matrix multiplication.
 * It needs to be passed another Matrix to initialise.
 * @param <E> Needs to extend from class Number
 * @param <T> Needs to extend from class Number
 */
public class SquareMatrix<E extends Number, T extends Number> extends Matrix<E>
                                          implements AddByMatrixMultiply<T, E>{
    /**
     * The constructor to initilaise a SquareMatrix object
     * @param n Dimension of the square matrix.
     * @param mat Matrix to initlaise from
     */
    public SquareMatrix(int n, Matrix<T> mat){
        super(n, n);
        addByMatrixMultiply(mat.getMatrix());
    }

    /**
     * The difference of two N-arrays are squared.
     * The value returned represents the similarity of the two array
     * @param a A Number array
     * @param b A Number array
     * @return Returns a float value due to that being the most applicable to
     *         all Number types
     */
    @Override
    public float takeDiffNSquare(Number[] a, Number[] b){
        int temp = 0;
        assert(a.length == b.length);
        int size = a.length;
        for(int i = 0; i < size; i++){
            temp += (b[i].intValue() - a[i].intValue()) *
                    (b[i].intValue() - a[i].intValue());
        }
        return ((float)((1 / (1 + Math.sqrt(temp)))));
    }

    /**
     * Initialises the 2d-Square-array/square-matrix.
     * @param matrix Matrix to initialise from.
     */
    @Override
    public void addByMatrixMultiply(Number matrix[][]){
        int r, c; // rows, cols
        r = m.length;
        c = m[0].length;
        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                m[i][j] = m[i][j] = takeDiffNSquare(matrix[i], matrix[j]);
        }

    }





}
