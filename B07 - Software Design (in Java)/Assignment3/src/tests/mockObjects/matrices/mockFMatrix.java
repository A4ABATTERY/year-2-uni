package tests.mockObjects.matrices;

public class mockFMatrix<E extends Number> extends mockMatrix<E> {

    public mockFMatrix(int r, int c){
        super(r,c);
    }

    public static <E extends Number> mockFMatrix<E> constructFileMatrix(String fileName){
        mockFMatrix<Number> mock = null;

        mock = new mockFMatrix<Number>(3,3);

        return (mockFMatrix<E>) mock;
    }
    public void addByFile(){
        this.m = new Integer[1][1];
        this.m[0][0] = 42;
        assert(this != null);

    }

}
