package tests.mockObjects.matrices;

public class mockSquareMatrix<E extends Number> extends mockMatrix<E> {

    public mockSquareMatrix(){
        super(1,1);
    }


    public float takeDiffNSquare(Integer[] a, Integer[]b){
        return (float)3.1415;
    }

    public void addByMatrixMultiply(Integer matrix[][]){
        this.m = matrix;
    }


}
