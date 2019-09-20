package tests.mockObjects.matrices;

import java.util.Iterator;

public class mockMatrix<E extends Number> implements Iterable<Number>{

    public Number m[][];

    public Iterator<Number> iterator(){
        return new matrixIter<Number>();

    }

    public class matrixIter<E> implements Iterator<E>{
        public Number iter = 5;

        public boolean hasNext(){
            iter = iter.intValue() -1;
            return (iter.intValue() > 0);
        }
        public E next(){
            if(hasNext())
                return (E) iter;
            return null;
        }


    }



    public mockMatrix(int r, int c){
        initialise();
        m = new Number[r][c];
    }
    public Number[][] getMatrix(){
        return m;
    }
    public void addValue(int r, int c, Number val){
        this.m[r][c] = val;
    }

    public Number get(int r, int c){
        //return m[r][c];
        return 9.0000;
    }
    public boolean checkIfSquare(){
        return false;
    }
    public String toString(){
        return "Some Str";
    }
    public void initialise(){
        m = null;
    }

    


}
