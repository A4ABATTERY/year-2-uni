package Matrices;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This is a container for a 2d array.
 * This particular class initialises itself using fileI/O.
 * It opens a file to grab data like the number of rows and columns it needs
 * to initialise, as well as the data that it needs to input into the array.
 *
 * @param <E> needs to be of/extend-from Class Number
 */
public class FMatrix<E extends Number> extends Matrix<E> implements AddByFile{

    /**
     * Calls the the super Constructor Matrix<E extends Number> to construct
     * the 2d Array/matrix
     * @param r How many rows in the array
     * @param c How many columns in the array
     */
    private FMatrix(int r, int c){
        super(r, c);
    }

    /**
     * This is the factory method for the class. It takes in a fileName to
     * initialise from.
     *
     * @param fileName pass a fileName to initialise from.
     * @param <E> needs to be of/extend-from Class Number
     * @return FMatrix<E> A constructed object of type FMatrix<E extends Number>
     */
    public static <E extends Number> 
                                  FMatrix<E> constructFMatrix(String fileName){
        FMatrix<E> nwMatrix = null;
        boolean added = true;
        try{ 
            FileInputStream fs = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            int r, c;
            r = Integer.parseInt(br.readLine());
            c = Integer.parseInt(br.readLine());
            br.readLine();
            nwMatrix = new FMatrix<E>(r, c);
            added = nwMatrix.addByFile(br);
        }catch(Exception e){
            return null;
        }
        if(added)
            return nwMatrix;
        else
            return null;
    }

    /**
     * Populates the array using an already open/initialise BufferedReader.
     *
     * @param file Takes in the an open/initialised BufferedReader to read and
     *             initialise the matrix
     */
    public boolean addByFile(BufferedReader file){
        try{
            String row;
            int i, j; i = j = -1;
            while((row = file.readLine()) != null){
                i++; j = -1;
                String[] strArr = row.split(" ");
                for(String str : strArr){
                    j++;
                    addValue(i, j, (Number) Integer.parseInt(str));
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
            return false;
        }
            
        return true;
    }


}
