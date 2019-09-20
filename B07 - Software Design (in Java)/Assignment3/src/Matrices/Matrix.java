package Matrices;

import java.util.Iterator;

/**
 * This is the container for a 2d-array/matrix that.
 * The class is iterable for convenience sake.
 *
 *
 * @param <E> needs to extend from class Number.
 */
public class Matrix<E extends Number> implements Iterable<Number>{


    /**
     * The 2d-array/matrix
     */
    protected Number m[][];
    /**
     * A var for the iterator
     */
    protected MatrixIter iterator;

    /**
     * The default constructor that can only be called on by the
     * children of Matrix
     */
    protected Matrix(){
        m = null;
    }

    /**
     * A non-default constructor that initialises the var 'm' using
     * two interger variables, rows and columns.
     * @param r How many rows to initialise
     * @param c How may columns to initialise.
     */
    protected Matrix(int r, int c){
        m = new Number[r][c];
        initialise();
    }

    /**
     * Returns the matrix so a uses can personm operations on it.
     *
     * @return returns the variable in the object 'm'
     */
   public Number[][] getMatrix(){
        return m;
   }

    /**
     * Get the value of of a specified point in the 2d-array/matrix
     *
     * @param r At what row
     * @param c At what column
     * @return return the Number at the specified row and column
     */
   public Number get(int r, int c){
        return m[r][c];
    }

    /**
     * Checks if the 2d-array/matrix is symmetrical
     * @return True/False
     */
    public boolean checkIfSquare(){
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                if(m[i][j] != m[j][i])
                    return false;

        return true;
    }

    /**
     * The matrixIter class
     */
    private abstract class MatrixIter{
        protected int iter, jter;
    }

    /**
     * The MatrixIterator used to fulfil the Iterator desing pattern.
     */
    public class MatrixIterator extends  MatrixIter implements Iterator<Number>{
        public MatrixIterator(){
            iter = jter = 0;
        }

        /**
         * Checks if the 2d-array/matrix has any remaining values to be
         * read
         * @return True/False
         */
        public boolean hasNext(){
            try{
                    Number temp = m[iter][jter];
                    return true;
            }catch(Exception e) {
                return false;
            }
        }

        /**
         * Returns back the next value in the 2d-array/matrix.
         * Used for for-each iteration.
         * @return Next Number in 2d-array/matrix
         */
        public Number next(){
            while(hasNext()){
                while(hasNext()){
                    Number temp =  m[iter][jter];
                    jter++;
                    return temp;
                }
                jter = 0;
                iter ++;
            }
            return null;
        }
    }

    /**
     * returns the iterator used for for-each iteration.
     * @return returns the iterator used for for-each iteration
     */
    public Iterator<Number> iterator(){
            iterator = new MatrixIterator();
            return (MatrixIterator)iterator;

    }

    /**
     * Returns the row at which the iteration is at.
     * @return int iter
     */
    public int getwhereIsIter(){
        return this.iterator.iter;
    }

    /**
     * Returns the column at which the iteration is at.
     * @return int jter
     */
    public int getwhereIsJter(){
        return this.iterator.jter;
    }

    /**
     * Adds/Overwrites the value at a given index
     * @param r Row
     * @param c Column
     * @param val Value to add/overwrite
     */
    public void addValue(int r, int c, Number val) {
            m[r][c] = val;

    }

    /**
     * Initialise everything as -1
     */
    protected void initialise(){
        for(int i = 0 ; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                m[i][j] = null;
    }

    public String toString(){
        String str = "";
        for(Number[] arg : m){
            for(Number a : arg)
                str += a.toString() + " ";
            str += '\n';
        }
        return str;
    }

    
}
